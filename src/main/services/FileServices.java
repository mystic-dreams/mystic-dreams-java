package main.services;

import main.Config;
import main.agents.Character;
import main.agents.JobClass;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.exceptions.InvalidJobException;
import main.services.filefilters.CharacterFileFilter;
import main.stats.Stats;
import main.stats.StatsBuilder;
import main.ui.Logger;
import main.ui.UI;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import static main.Constants.*;
import static main.ui.UI.newline;

public class FileServices {
    public static String[] getCharacters() throws IOException {
        ArrayList<String> characterNames = new ArrayList<>();

        File dataFolder = new File(Config.DATA_FOLDER_PATH);

        if (!dataFolder.exists() && !dataFolder.mkdirs()) { // Create data directory if don't exist
            throw new IOException();
        }

        File[] dataFiles = dataFolder.listFiles(new CharacterFileFilter());

        if (dataFiles == null) {
            throw new IOException();
        }

        for (File dataFile : dataFiles) {
            characterNames.add(dataFile.getName().split("\\.")[0]);
        }

        return characterNames.toArray(new String[]{});
    }

    public static boolean characterExists(String characterName) {
        try {
            File dataFolder = new File(getDataFilePath(characterName, GameDataType.CHARACTER));
            return dataFolder.exists();
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public static void writeCharacterToFile(Character character) throws IOException, InterruptedException {
        Logger.info("Creating character");
        String characterDataFilePath = getDataFilePath(character.name, GameDataType.CHARACTER);
        Logger.debug("Writing to " + characterDataFilePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(characterDataFilePath))) {
            writer.write(character.toFileFormat());
        }

        String skillDataFilePath = getDataFilePath(character.name, GameDataType.SKILL);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(skillDataFilePath))) {
            writer.write(character.skillBook.toFileFormat());
        }

        File characterFile = new File(characterDataFilePath);
        File skillFile = new File(skillDataFilePath);
        while (!characterFile.exists() || !skillFile.exists()) { // Wait for the files to be created.
            sleep(1000);
            UI.print(".");
        }
        newline();
        Logger.info("Character created");
    }

    public static Character loadCharacter(String characterName) throws CharacterNotFoundException,
            DataCorruptedException, InvalidJobException, InterruptedException {
        String characterDataFilePath =
                Config.DATA_FOLDER_PATH + "/" + characterName + Config.CHARACTER_DATA_FILE_EXTENSION;
        try {
            Map<String, String> characterData = parseFile(characterDataFilePath);
            Stats stats = new StatsBuilder()
                    .setStr(parseInt(characterData.get(STR_STAT_FIELD)))
                    .setWis(parseInt(characterData.get(WIS_STAT_FIELD)))
                    .setMaxHP(parseInt(characterData.get(MAX_HP_STAT_FIELD)))
                    .setMaxMP(parseInt(characterData.get(MAX_MP_STAT_FIELD)))
                    .setWDef(parseInt(characterData.get(WDEF_STAT_FIELD)))
                    .setMDef(parseInt(characterData.get(MDEF_STAT_FIELD)))
                    .setAvd(parseInt(characterData.get(AVD_STAT_FIELD)))
                    .setAcc(parseInt(characterData.get(ACC_STAT_FIELD)))
                    .setSpd(parseInt(characterData.get(SPD_STAT_FIELD)))
                    .setCritRate(parseInt(characterData.get(CRIT_RATE_STAT_FIELD)))
                    .setCritDmg(parseInt(characterData.get(CRIT_DMG_STAT_FIELD)))
                    .setWAtt(parseInt(characterData.get(WATT_STAT_FIELD)))
                    .setMAtt(parseInt(characterData.get(MATT_STAT_FIELD)))
                    .setWMast(parseInt(characterData.get(WMAST_STAT_FIELD)))
                    .build();

            return new Character(
                    characterData.get(NAME_FIELD),
                    parseInt(characterData.get(LEVEL_FIELD)),
                    parseInt(characterData.get(HP_FIELD)),
                    parseInt(characterData.get(MP_FIELD)),
                    stats,
                    JobClass.parse(characterData.get(JOB_FIELD)),
                    parseInt(characterData.get(AP_FIELD)),
                    parseInt(characterData.get(SP_FIELD)));
        } catch (FileNotFoundException e) {
            throw new CharacterNotFoundException(characterName);
        } catch (NumberFormatException e) {
            throw new DataCorruptedException(characterDataFilePath);
        }
    }

    // ========================================
    //  Helper
    // ========================================
    public static Map<String, String> parseFile(String path) throws FileNotFoundException, DataCorruptedException {
        Map<String, String> data = new HashMap<>();
        Scanner reader = new Scanner(new File(path));
        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            if (line.startsWith("#")) {
                // Treat # as comment
                continue;
            }

            String[] keyValuePair = line.split("=");

            if (keyValuePair.length != 2) {
                throw new DataCorruptedException(getFilenameFromPath(path));
            }
            data.put(keyValuePair[0].trim(), keyValuePair[1].trim());
        }
        reader.close();
        return data;
    }

    private static String getDataFilePath(String characterName, GameDataType type) throws FileNotFoundException {
        return Config.DATA_FOLDER_PATH + "/" + characterName + getExtension(type);
    }

    private static String getExtension(GameDataType type) throws FileNotFoundException {
        switch (type) {
            case CHARACTER -> {
                return Config.CHARACTER_DATA_FILE_EXTENSION;
            }
            case SKILL -> {
                return Config.SKILL_DATA_FILE_EXTENSION;
            }
            default -> {
                throw new FileNotFoundException();
            }
        }
    }

    private static String getFilenameFromPath(String path) {
        String[] pathComponents = path.split("=");
        return pathComponents[pathComponents.length - 1];
    }


}
