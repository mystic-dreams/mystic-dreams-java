package main.ui.screens;

import main.agents.Character;
import main.stats.Stats;

import static main.ui.UI.*;

public class CharacterStatsScreen {

    Character character;

    public CharacterStatsScreen(Character character) {
        this.character = character;
    }

    public void show() throws InterruptedException {
        Stats characterStats = character.getStats();

        println(character.name);
        println("Level: " + character.level);
        println("==================");
        println("hp: " + character.getHP() + "/" + characterStats.maxHP.getValue());
        println("mp: " + character.getMP() + "/" + characterStats.maxMP.getValue());
        newline();
        println("Stats");
        println("-----");
        println("Strength: " + characterStats.str.getValue());
        println("Wisdom: " + characterStats.wis.getValue());
        newline();
        println("Avoid: " + characterStats.avd.getValue());
        println("Acc: " + characterStats.acc.getValue());
        println("Speed: " + characterStats.spd.getValue());
        newline();
        println("W. Att: " + characterStats.wAtt.getValue());
        println("M. Att: " + characterStats.mAtt.getValue());
        println("W. Mastery: " + characterStats.wMast.getValue());
        newline();

        getInput("Press enter to continue...");
    }
}
