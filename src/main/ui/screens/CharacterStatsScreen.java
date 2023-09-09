package main.ui.screens;

import main.agents.character.Character;

import static main.ui.UI.*;

public class CharacterStatsScreen {

    Character character;

    public CharacterStatsScreen(Character character) {
        this.character = character;
    }

    public void show() throws InterruptedException {
        println(character.name);
        println("Level: " + character.getLevel());
        println("==================");
        println("hp: " + character.getHP() + "/" + character.stats.maxHP.getValue());
        println("mp: " + character.getMP() + "/" + character.stats.maxMP.getValue());
        newline();
        println("Stats");
        println("-----");
        println("Strength: " + character.stats.str.getValue());
        println("Wisdom: " + character.stats.wis.getValue());
        newline();
        println("W. Def: " + character.stats.wDef.getValue());
        println("M. Def: " + character.stats.mDef.getValue());
        println("Avoid: " + character.stats.avd.getValue());
        println("Acc: " + character.stats.acc.getValue());
        println("Speed: " + character.stats.spd.getValue());
        println("Crit Rate: " + character.stats.critRate.getValue() + "%");
        println("Crit Damage: " + character.stats.critDmg.getValue() + "%");
        newline();
        println("W. Att: " + character.stats.wAtt.getValue());
        println("M. Att: " + character.stats.mAtt.getValue());
        println("W. Mastery: " + character.stats.wMast.getValue() + "%");
        newline();

        getInput("Press enter to continue...");
    }
}
