package main.skills.skillbooks;

import main.exceptions.LevelExceededException;
import main.skills.attack.magician.ManaBolt;
import main.skills.buff.magician.ManaArmor;
import main.skills.passive.magician.ManaWell;
import main.ui.Logger;

public class MagicianSkillBook extends SkillBook {

    private MagicianSkillBook() {
    }

    public static SkillBook init() {
        try {
            return new SkillBook(ManaWell.invoke(0), ManaBolt.invoke(1), ManaArmor.invoke(0));
        } catch (LevelExceededException e) {
            // Should not happen...
            Logger.error("Error while loading skill book. Spell level exceeds max level.");
            return null;
        }
    }
}
