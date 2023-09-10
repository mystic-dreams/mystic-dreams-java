package main.ui.screens;

import main.agents.character.Character;
import main.agents.character.JobClass;
import main.exceptions.InvalidOptionException;
import main.services.FileServices;
import main.ui.Logger;

import java.io.IOException;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static main.Messages.INVALID_SELECTION_MESSAGE;
import static main.Messages.SELECTION_PROMPT;
import static main.ui.UI.*;

public class CharacterCreationScreen extends Screen {

    private Character character = null;

    @Override
    protected void handleScreenLogic() throws InterruptedException {
        // Confirm to proceed with character creation
        if (!confirmToProceed()) {
            return;
        }

        // Get character name
        String characterName;
        while (true) {
            characterName = getInput("Character Name:");
            newline();

            // Validate name
            if (!validateName(characterName)) {
                notice("Invalid name. Name cannot contain symbols, whitespace or exceed 18 characters");
                continue;
            }

            // Check if character already exists
            if (FileServices.characterExists(characterName)) {
                notice("Character already exists");
                continue;
            }
            break;
        }

        // Get job class
        JobClass[] jobClasses = JobClass.values();
        JobClass jobClass;
        while (true) {
            try {
                println("Choose your character class:");
                for (int i = 0; i < jobClasses.length; i++) {
                    println((i + 1) + ". " + jobClasses[i].value);
                }
                newline();
                int selection = parseInt(getInput(SELECTION_PROMPT));

                if (selection < 1 || selection > jobClasses.length) {
                    throw new InvalidOptionException();
                }

                jobClass = jobClasses[selection - 1];
                break;
            } catch (NumberFormatException | InvalidOptionException e) {
                notice(INVALID_SELECTION_MESSAGE);
            } finally {
                newline();
            }
        }

        try {
            Logger.info("Creating character");
            // Save character
            character = new Character(characterName, jobClass);
            FileServices.writeCharacterToFile(character);
            Logger.info("Character created");
        } catch (IOException e) {
            Logger.error("Unable to save user. Please make sure that the data file is not opened or try again later.");
        }
    }

    // ========================================
    //  Helper
    // ========================================
    public Character getCharacter() {
        return character;
    }

    private boolean confirmToProceed() {
        String input = getInput("There is no returning once you proceed with the character creation. Do you wish to proceed? (y/N)");
        String cleanedInput = input.trim().toLowerCase();
        return cleanedInput.equals("y") || cleanedInput.equals("yes");
    }

    /*
        Name Rules:
        1) Alphanumeric only
        2) Up to 18 characters
     */
    private boolean validateName(String name) {
        Pattern alphanumeric = Pattern.compile("^[a-zA-Z0-9]{1,18}$");

        return alphanumeric.matcher(name).find();

    }

    // ========================================
    //  Constructor
    // ========================================
    public CharacterCreationScreen() {
        super(false);
    }
}
