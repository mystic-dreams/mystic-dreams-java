package main.services;

import main.Config;
import main.agents.Character;
import main.agents.JobClass;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.exceptions.InvalidJobException;
import main.services.filefilters.CharacterFileFilter;
import main.stats.Stats;
import main.ui.Logger;
import main.ui.UI;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
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
        File dataFolder = new File(getCharacterDataFilePath(characterName));
        return dataFolder.exists();
    }

    public static void writeCharacterToFile(Character character) throws IOException, InterruptedException {
        Logger.info("Creating character");
        String characterDataFilePath = getCharacterDataFilePath(character.name);
        Logger.debug("Writing to " + characterDataFilePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(characterDataFilePath))) {
            writer.write(character.toFileFormat());
        }

        File characterFile = new File(characterDataFilePath);
        while (!characterFile.exists()) { // Wait for the file to be created.
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
            Stats stats = new Stats(
                    parseInt(characterData.get("str")),
                    parseInt(characterData.get("wis")),
                    parseInt(characterData.get("maxHP")),
                    parseInt(characterData.get("maxMP")),
                    parseInt(characterData.get("avd")),
                    parseInt(characterData.get("acc")),
                    parseInt(characterData.get("spd")),
                    parseInt(characterData.get("wAtt")),
                    parseInt(characterData.get("mAtt")),
                    parseInt(characterData.get("wMast"))
            );
            return new Character(
                    characterData.get("name"),
                    parseInt(characterData.get("level")),
                    parseInt(characterData.get("hp")),
                    parseInt(characterData.get("mp")),
                    stats,
                    JobClass.parse(characterData.get("job")),
                    parseInt(characterData.get("ap")),
                    parseInt(characterData.get("sp")));
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

    private static String getCharacterDataFilePath(String characterName) {
        return Config.DATA_FOLDER_PATH + "/" + characterName + Config.CHARACTER_DATA_FILE_EXTENSION;
    }

    private static String getFilenameFromPath(String path) {
        String[] pathComponents = path.split("=");
        return pathComponents[pathComponents.length - 1];
    }


}
