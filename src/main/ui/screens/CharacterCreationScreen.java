package main.ui.screens;

import main.agents.Character;
import main.services.FileServices;
import main.ui.Logger;
import main.ui.UI;
import main.utility.Validator;

import java.io.IOException;

import static main.ui.UI.notice;

public class CharacterCreationScreen {

    public void show() throws InterruptedException {
        while (true) {
            String characterName = UI.getInput("Character name:");

            if (!Validator.validateName(characterName)) {
                notice("Invalid name. Name cannot contain symbols, whitespace or exceed 18 characters");
                continue;
            }

            if (FileServices.characterExists(characterName)) {
                notice("Character already exists");
                continue;
            }

            try {
                // Save character
                FileServices.writeCharacterToFile(new Character(characterName));
            } catch (IOException e) {
                Logger.error("Unable to save user. Please make sure that the data file is not opened or try again later.");
            }
            break;
        }
    }
}
