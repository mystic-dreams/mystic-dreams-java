package main.skills.attack;

import main.skills.ActiveSkill;
import main.skills.AttackType;
import main.stats.Stats;

import java.util.Random;

import static main.Config.DMG_PER_ATT;
import static main.Config.STATS_PER_ATT;

public abstract class AttackSkill extends ActiveSkill {
    public final AttackType attackType;
    private final int[] skillDamages;
    private final int numOfHits;
    private final Random rand = new Random();

    protected AttackSkill(String name, int maxLevel, int level, int[] hpConsumption, int[] mpConsumptions,
                          int[] skillDamages,
                          int numOfHits, AttackType attackType) {
        super(name, maxLevel, level, hpConsumption, mpConsumptions);
        this.skillDamages = skillDamages;
        this.numOfHits = numOfHits;
        this.attackType = attackType;
    }

    public long calculateDamage(Stats casterStats) {
        int mainStat = attackType == AttackType.PHYSICAL ? casterStats.str.getValue() : casterStats.wis.getValue();
        int weaponStat = attackType == AttackType.PHYSICAL ? casterStats.wAtt.getValue() : casterStats.mAtt.getValue();
        long totalDmg = 0;
        float skillDamageNormalized = this.skillDamages[level] / 100f;
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
