package main.ui.menus;

import main.agents.Character;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.exceptions.InvalidOptionException;
import main.services.FileServices;
import main.ui.Logger;
import main.ui.screens.CharacterCreationScreen;
import main.utility.OptionsBuilder;

import java.io.IOException;

import static main.ui.UI.*;

public class CharacterSelectionMenu extends Menu {
    @Override
    public void show() throws InterruptedException {
        while (true) {
            if (banner != null) {
                println(banner.toString());
                newline();
            }

            if (prompt != null) {
                println(prompt);
            }

            try {
                options = addDefaultOptions(FileServices.getCharacters());

                displayOptions();
                newline();

                int selection = Integer.parseInt(getInput("Selection:"));
                newline();

                if (selection > options.length) {
                    throw new InvalidOptionException();
                }

                if (selection == options.length) {
                    return;
                }

                handleSelection(selection);

            } catch (NumberFormatException | InvalidOptionException e) {
                notice("Invalid Input");
            } catch (IOException e) {
                Logger.error("Unable to get characters.");
                return;
            } finally {
                newline();
            }
        }
    }

    @Override
    protected void handleSelection(int selection) throws InterruptedException {
        if (selection == options.length - 1) {
            new CharacterCreationScreen().show();
        } else {
            try {
                Character character = FileServices.loadCharacter(options[selection - 1]);
                Logger.debug(character.toString());
            } catch (DataCorruptedException | CharacterNotFoundException e) {
                Logger.error(e.getMessage());
            }
        }
    }

    // ========================================
    //  Helper
    // ========================================

    private static String[] addDefaultOptions(String[] options) {
        String[] newOptions = new String[options.length + 1];
        System.arraycopy(options, 0, newOptions, 0, options.length);
        newOptions[newOptions.length - 1] = "Create new character";
        return new OptionsBuilder().addOptions(newOptions).setReturnType(MenuReturnType.BACK).build();
    }


    // ========================================
    //  Constructors
    // ========================================
    public CharacterSelectionMenu() throws IOException {
        super(null, "Select your character:");
    }
}
