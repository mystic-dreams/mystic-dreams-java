package main.ui.screens.menus;

import main.agents.character.Character;
import main.agents.monsters.Monster;

public class CombatMenu extends Menu {
    private final Character character;
    private final Monster monster;

    @Override
    boolean handleSelection(int selection) throws InterruptedException {
        switch (selection) {
            case 1 -> {
                // Open skill menu
                return new SkillCombatMenu(character, monster).show();
            }
            default -> {
                return false;
            }
        }
    }

    // ========================================
    //  Constructor
    // ========================================
    protected CombatMenu(Character character, Monster monster) {
        super("Choose an action", MenuReturnType.ESCAPE, false, "Skills");
        this.character = character;
        this.monster = monster;
    }
}
