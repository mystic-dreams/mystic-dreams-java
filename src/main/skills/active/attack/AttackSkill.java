package main.skills.active.attack;

import main.skills.active.ActiveSkill;
import main.stats.Stats;

import java.util.Random;

import static main.Config.DMG_PER_ATT;
import static main.Config.STATS_PER_ATT;

public abstract class AttackSkill extends ActiveSkill {

    private int skillDamage;
    private int numOfHits;
    private final AttackType attackType;
    private final Random rand = new Random();

    public AttackSkill(String name, int maxLevel, int level, int hpConsumption, int mpConsumption, int skillDamage,
                       int numOfHits, AttackType attackType) {
        super(name, maxLevel, level, hpConsumption, mpConsumption);
        this.skillDamage = skillDamage;
        this.numOfHits = numOfHits;
        this.attackType = attackType;
    }

    long calculateDamage(Stats casterStats) {
        int mainStat = attackType == AttackType.PHYSICAL ? casterStats.str.getValue() : casterStats.wis.getValue();
        int weaponStat = attackType == AttackType.PHYSICAL ? casterStats.wAtt.getValue() : casterStats.mAtt.getValue();
        long totalDmg = 0;
        float skillDamageNormalized = this.skillDamage / 100f;
        float wMastNormalized = casterStats.wMast.getValue() / 100f;
        float criticalDamageNormalized = casterStats.critDmg.getValue() / 100f;

        int statAttPoints = mainStat / STATS_PER_ATT;
        float attPoints = statAttPoints + weaponStat * wMastNormalized;

        for (int i = 0; i < numOfHits; i++) {
            int critPoint = rand.nextInt(101); // Upper bound is exclusive
            boolean isCrit = critPoint <= casterStats.critRate.getValue();
            totalDmg += Math.round(
                    skillDamageNormalized * attPoints * DMG_PER_ATT * (isCrit ? criticalDamageNormalized
                            : 1)
            );
        }

        return totalDmg;
    }
}
