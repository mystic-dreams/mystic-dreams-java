package main.ui.screens.menus;

import main.services.FileServices;
import main.ui.Logger;

import java.io.IOException;

import static main.Constants.APPLICATION_NAME;
import static main.Messages.INVALID_SELECTION_MESSAGE;
import static main.Messages.UNABLE_TO_READ_FILE_MESSAGE;
import static main.ui.UI.notice;

public class MainMenu extends Menu {

    @Override
    boolean handleSelection(int selection) throws InterruptedException {
        switch (selection) {
            case 1 -> {
                // Get character list
                try {
                    String[] characterNames = FileServices.getCharacters();
                    return new CharacterSelectionMenu(characterNames).show();
                } catch (IOException e) {
                    notice(UNABLE_TO_READ_FILE_MESSAGE);
                }
            }
            default -> {
                // Should not reach here
                Logger.error(INVALID_SELECTION_MESSAGE);
            }
        }
        return false;
    }

    // ========================================
    //  Constructor
    // ========================================
    public MainMenu() {
        super(new Banner(APPLICATION_NAME), "Welcome to " + APPLICATION_NAME, MenuReturnType.EXIT, false, "Start Game");
    }
}
