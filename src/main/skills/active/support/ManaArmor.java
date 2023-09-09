package main.skills.active.support;

import main.stats.Stats;
import main.stats.StatsBuilder;

import static main.Constants.MS_PER_MIN;
import static main.skills.SkillNames.MANA_ARMOR_NAME;

public class ManaArmor extends BuffSkill {

    private static final Stats[] effects = {
            new StatsBuilder().setWDef(0.00f).setMDef(0.00f).build(),
            new StatsBuilder().setWDef(0.10f).setMDef(0.10f).build(),
            new StatsBuilder().setWDef(0.11f).setMDef(0.11f).build(),
            new StatsBuilder().setWDef(0.12f).setMDef(0.12f).build(),
            new StatsBuilder().setWDef(0.13f).setMDef(0.13f).build(),
            new StatsBuilder().setWDef(0.14f).setMDef(0.14f).build(),
            new StatsBuilder().setWDef(0.15f).setMDef(0.15f).build(),
            new StatsBuilder().setWDef(0.16f).setMDef(0.16f).build(),
            new StatsBuilder().setWDef(0.17f).setMDef(0.17f).build(),
            new StatsBuilder().setWDef(0.18f).setMDef(0.18f).build(),
            new StatsBuilder().setWDef(0.20f).setMDef(0.20f).build(),
    };

    private static final int[] mpConsumptions = {0, 10, 12, 12, 14, 14, 16, 16, 18, 18, 20};

    public ManaArmor(int level) {
        super(MANA_ARMOR_NAME, effects.length - 1, level, 0, mpConsumptions[level], effects[level], 3 * MS_PER_MIN);
    }

    public ManaArmor() {
        this(0);
    }
}
