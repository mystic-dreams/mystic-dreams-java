package main.ui.screens;

import main.agents.Character;
import main.agents.JobClass;
import main.exceptions.InvalidOptionException;
import main.services.FileServices;
import main.ui.Logger;
import main.utility.Validator;

import java.io.IOException;

import static java.lang.Integer.parseInt;
import static main.Messages.INVALID_SELECTION;
import static main.ui.UI.*;

public class CharacterCreationScreen {

    private final JobClass[] jobClasses = JobClass.values();

    public void show() throws InterruptedException {
        String characterName;
        JobClass jobClass;

        while (true) {
            characterName = getInput("Character name:");
            newline();

            if (!Validator.validateName(characterName)) {
                notice("Invalid name. Name cannot contain symbols, whitespace or exceed 18 characters");
                continue;
            }

            if (FileServices.characterExists(characterName)) {
                notice("Character already exists");
                continue;
            }
            break;
        }

        while (true) {
            try {
                println("Choose your character class:");
                for (int i = 0; i < jobClasses.length; i++) {
                    println((i + 1) + ". " + jobClasses[i].value);
                }
                newline();
                int selection = parseInt(getInput("Selection:"));

                if (selection < 1 || selection > jobClasses.length) {
                    throw new InvalidOptionException();
                }

                jobClass = jobClasses[selection - 1];
                break;
            } catch (NumberFormatException | InvalidOptionException e) {
                notice(INVALID_SELECTION);
            } finally {
                newline();
            }
        }


        try {
            // Save character
            FileServices.writeCharacterToFile(new Character(characterName, jobClass));
        } catch (IOException e) {
            Logger.error("Unable to save user. Please make sure that the data file is not opened or try again later.");
        }
    }
}
