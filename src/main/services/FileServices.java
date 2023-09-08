package main.services;

import main.Config;
import main.agents.Character;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.services.filefilters.CharacterFileFilter;
import main.ui.Logger;
import main.ui.UI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        String characterDataFilePath = getCharacterDataFilePath(character.getName());
        Logger.debug("Writing to " + characterDataFilePath);
        try (FileWriter fw = new FileWriter(characterDataFilePath)) {
            fw.write("name=" + character.getName());
        }

        File characterFile = new File(characterDataFilePath);
        while (!characterFile.exists()) { // Wait for the file to be created.
            Thread.sleep(1000);
            UI.print(".");
        }
        newline();
        Logger.info("Character created");
    }

    public static Character loadCharacter(String characterName) throws CharacterNotFoundException, DataCorruptedException {
        String characterDataFilePath = Config.DATA_FOLDER_PATH + "/" + characterName + Config.CHARACTER_DATA_FILE_EXTENSION;
        try {
            Map<String, String> characterData = parseFile(characterDataFilePath);
            return new Character(characterData.get("name"));
        } catch (FileNotFoundException e) {
            throw new CharacterNotFoundException(characterName);
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
