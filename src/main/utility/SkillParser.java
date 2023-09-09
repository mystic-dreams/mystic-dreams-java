package main.utility;

import main.exceptions.InvalidSkillException;
import main.exceptions.LevelExceededException;
import main.skills.Skill;
import main.skills.attack.magician.ManaBolt;
import main.skills.buff.magician.ManaArmor;
import main.skills.passive.magician.ManaWell;

import static main.skills.SkillNames.*;

public class SkillParser {
    public static Skill parseSkill(String skillName, int level) throws LevelExceededException, InvalidSkillException {

        Skill skill;
        switch (skillName) {
            case MANA_WELL_NAME:
                skill = ManaWell.invoke(level);
                break;
            case MANA_ARMOR_NAME:
                skill = ManaArmor.invoke(level);
                break;
            case MANA_BOLT_NAME:
                skill = ManaBolt.invoke(level);
                break;
            default:
                throw new InvalidSkillException(skillName);
        }

        return skill;
    }
}
