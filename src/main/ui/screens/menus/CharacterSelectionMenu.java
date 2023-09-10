package main.ui.screens.menus;

import main.agents.character.Character;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.exceptions.InvalidJobException;
import main.ui.screens.CharacterCreationScreen;
import main.ui.screens.GameScreen;

import java.util.Arrays;

import static main.services.FileServices.loadCharacter;
import static main.ui.UI.notice;

public class CharacterSelectionMenu extends Menu {
    @Override
    boolean handleSelection(int selection) throws InterruptedException {
        Character character;
        if (selection == options.length - 1) {
            // Second-last option: Create character
            CharacterCreationScreen characterCreationScreen = new CharacterCreationScreen();
            boolean escape = characterCreationScreen.show();
            character = characterCreationScreen.getCharacter();;
            if (character == null) {
                return escape;
            }
        } else {
            try {
                character = loadCharacter(options[selection - 1]);
            } catch (CharacterNotFoundException | DataCorruptedException | InvalidJobException e) {
                notice(e.getMessage());
                return false;
            }
        }

        return new GameScreen(character).show();
    }

    // ========================================
    //  Helper
    // ========================================
    private static String[] addCreateCharacterOption(String[] characterNames) {
        String[] newOptionsArray = Arrays.copyOf(characterNames, characterNames.length + 1);
        newOptionsArray[newOptionsArray.length - 1] = "Create new character";
        return newOptionsArray;
    }

    // ========================================
    //  Constructor
    // ========================================
    public CharacterSelectionMenu(String... characterNames) {
        super("Select your character:", addCreateCharacterOption(characterNames));
    }
}
