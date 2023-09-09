package main.ui.menus;

import main.agents.character.Character;
import main.agents.monsters.Monster;
import main.agents.monsters.Slime;
import main.exceptions.CharacterNotFoundException;
import main.exceptions.DataCorruptedException;
import main.exceptions.InvalidJobException;
import main.exceptions.InvalidOptionException;
import main.services.FileServices;
import main.skills.ActiveSkill;
import main.skills.SupportSkill;
import main.skills.attack.AttackSkill;
import main.ui.Logger;
import main.ui.screens.CharacterCreationScreen;
import main.utility.OptionsBuilder;

import java.io.IOException;

import static main.Config.COMBAT_DISPLAY_DELAY;
import static main.Messages.INVALID_SELECTION;
import static main.skills.SkillNames.MANA_BOLT_NAME;
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

            } catch (IOException e) {
                Logger.error("Unable to get characters.");
                return;
            } catch (InvalidOptionException e) {
                notice(INVALID_SELECTION);
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
                Monster enemy = new Slime();
                boolean escape = false;
                while (!escape) {
                    AttackSkill manaBolt = (AttackSkill) character.getSkill(MANA_BOLT_NAME);
                    character.attack(manaBolt, enemy);

                    if (enemy.isDead()) {
                        println(enemy.name + " is dead.", COMBAT_DISPLAY_DELAY);
                        character.gainExp(enemy.getExp());
//                        escape = true;
                        break;
                    }

                    ActiveSkill enemySkill = enemy.getSkill();
                    if (enemySkill instanceof SupportSkill) {
                        ((SupportSkill) enemySkill).apply(enemy);
                    } else {
                        enemy.attack((AttackSkill) enemySkill, character);
                    }

                    if (character.isDead()) {
                        println(character.name + " is dead.", COMBAT_DISPLAY_DELAY);
//                        escape = true;
                        break;
                    }

                }

            } catch (DataCorruptedException | CharacterNotFoundException | InvalidJobException e) {
                Logger.error(e.getMessage());
            } catch (IOException e) {
                Logger.error("Unable to save character");
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
