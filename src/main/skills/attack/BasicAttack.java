package main.skills.attack;

import main.skills.AttackType;

import static main.skills.SkillNames.BASIC_ATTACK_NAME;

public class BasicAttack extends AttackSkill {
    public BasicAttack() {
        super(BASIC_ATTACK_NAME, 1, 1, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 100}, 1, AttackType.PHYSICAL);
    }
}
