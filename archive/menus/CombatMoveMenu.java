package main.ui.menus;

import main.agents.character.Character;
import main.agents.monsters.Monster;

import static main.ui.UI.notice;

public class CombatMoveMenu extends Menu {
    private final Character character;
    private final Monster monster;

    public CombatMoveMenu(Character character, Monster monster) {
        super(null, "Choose an action:", MenuReturnType.ESCAPE, "Skills", "Inventory");
        this.character = character;
        this.monster = monster;
    }

    @Override
    protected boolean handleSelection(int selection) throws InterruptedException {
        switch (selection) {
            case 1 -> new CombatMenu(character, monster).show();

            case 2 -> notice("Not implemented");
        }
        return false;
    }
}
