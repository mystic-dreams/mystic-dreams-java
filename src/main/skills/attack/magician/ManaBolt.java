package main.skills.attack.magician;

import main.skills.AttackType;
import main.skills.attack.AttackSkill;

import static main.skills.SkillNames.MANA_BOLT_NAME;

public class ManaBolt extends AttackSkill {
    private static final int[] skillDamages = {0, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130,
            135, 140, 145, 150};
    private static final int[] mpConsumptions = {0, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16,
            16, 17, 17};


    private ManaBolt(int level) {
        super(MANA_BOLT_NAME, skillDamages.length - 1, level, null, mpConsumptions, skillDamages, 2,
                AttackType.MAGICAL);
    }

    public ManaBolt() {
        this(0);
    }
}
