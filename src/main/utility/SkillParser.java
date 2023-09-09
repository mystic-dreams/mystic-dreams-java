package main.utility;

import main.exceptions.InvalidSkillException;
import main.exceptions.LevelExceededException;
import main.skills.Skill;
import main.skills.buff.magician.ManaArmor;
import main.skills.passive.magician.ManaWell;

import static main.skills.SkillNames.MANA_ARMOR_NAME;
import static main.skills.SkillNames.MANA_WELL_NAME;

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
            default:
                throw new InvalidSkillException(skillName);
        }

        return skill;
    }
}
