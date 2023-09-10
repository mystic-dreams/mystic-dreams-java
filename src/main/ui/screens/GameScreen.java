package main.ui.screens;

import main.agents.character.Character;
import main.agents.monsters.Slime;
import main.ui.screens.menus.CombatScreen;

public class GameScreen extends Screen {

    private Character character;

    @Override
    protected void handleScreenLogic() throws InterruptedException {
        new CombatScreen(character, new Slime()).show();
    }

    // ========================================
    //  Constructor
    // ========================================
    public GameScreen(Character character) {
        // Previous to this should be character selection screen. Skipping that should return to main menu.
        super(true);
        this.character = character;
    }
}
