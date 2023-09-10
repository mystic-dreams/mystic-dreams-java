package main.ui.menus;

import main.agents.character.Character;
import main.agents.monsters.Slime;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.exceptions.InvalidJobException;
import main.exceptions.InvalidOptionException;
import main.services.FileServices;
import main.ui.Logger;
import main.ui.screens.CharacterCreationScreen;
import main.ui.screens.CombatScreen;
import main.utility.OptionsBuilder;

import java.io.IOException;

import static main.Messages.INVALID_SELECTION;
import static main.Messages.SELECTION_PROMPT;
import static main.ui.UI.*;

public class CharacterSelectionMenu extends Menu {
    @Override
    public boolean show() throws InterruptedException {
        boolean escape = false;
        while (!escape) {
            if (banner != null) {
                println(banner.toString());
                newline();
            }

            if (prompt != null) {
                println(prompt);
            }

            try {
                options = addDefaultOptions(FileServices.getCharacters());

                displayOptions(options);

                int selection = Integer.parseInt(getInput(SELECTION_PROMPT));
                newline();

                if (selection > options.length) {
                    throw new InvalidOptionException();
                }

                if (selection == options.length) {
                    // Returns true for breaking out of loop if last option is escape/exit, false if the last option is
                    // back. Useful for nested menus.
                    return false;
                }

                escape = handleSelection(selection);

            } catch (IOException e) {
                Logger.error("Unable to get characters.");
            } catch (InvalidOptionException e) {
                notice(INVALID_SELECTION);
            } finally {
                newline();
            }
        }
        return skipPreviousScreen;
    }

    @Override
    protected boolean handleSelection(int selection) throws InterruptedException {
        if (selection == options.length - 1) {
            return new CharacterCreationScreen().show();
        } else {
            try {
                Character character = FileServices.loadCharacter(options[selection - 1]);
                new CombatScreen(character, new Slime()).show();
                return true;
            } catch (DataCorruptedException | CharacterNotFoundException | InvalidJobException e) {
                Logger.error(e.getMessage());
            }
        }
        return false;
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
        super(null, "Select your character:", false);
    }
}
