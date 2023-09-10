package main.skills.skillbooks;

import main.exceptions.LevelExceededException;
import main.skills.attack.BasicAttack;
import main.skills.passive.warrior.IronSkin;
import main.ui.Logger;

public class WarriorSkillBook extends SkillBook {
    private WarriorSkillBook() {
    }

    public static SkillBook init() {
        try {
            return new SkillBook(new BasicAttack(), IronSkin.invoke(0));
        } catch (LevelExceededException e) {
            // Should not happen...
            Logger.error("Error while loading skill book. Spell level exceeds max level.");
            return null;
        }
    }
}
