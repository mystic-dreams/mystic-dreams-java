package main.skills.passive;

import main.stats.Stats;
import main.stats.StatsBuilder;

import static main.skills.SkillNames.MANA_WELL_NAME;

public class ManaWell extends PassiveSkill {
    private static final Stats[] skillEffects = {
            new StatsBuilder().setMaxMP(0.00f).build(),
            new StatsBuilder().setMaxMP(0.00f).build(),
            new StatsBuilder().setMaxMP(0.01f).build(),
            new StatsBuilder().setMaxMP(0.02f).build(),
            new StatsBuilder().setMaxMP(0.03f).build(),
            new StatsBuilder().setMaxMP(0.04f).build(),
            new StatsBuilder().setMaxMP(0.05f).build(),
            new StatsBuilder().setMaxMP(0.06f).build(),
            new StatsBuilder().setMaxMP(0.07f).build(),
            new StatsBuilder().setMaxMP(0.08f).build(),
            new StatsBuilder().setMaxMP(0.09f).build(),
            new StatsBuilder().setMaxMP(0.10f).build(),
            new StatsBuilder().setMaxMP(0.11f).build(),
            new StatsBuilder().setMaxMP(0.12f).build(),
            new StatsBuilder().setMaxMP(0.13f).build(),
            new StatsBuilder().setMaxMP(0.14f).build(),
            new StatsBuilder().setMaxMP(0.15f).build(),
            new StatsBuilder().setMaxMP(0.16f).build(),
            new StatsBuilder().setMaxMP(0.17f).build(),
            new StatsBuilder().setMaxMP(0.18f).build(),
            new StatsBuilder().setMaxMP(0.19f).build(),
            new StatsBuilder().setMaxMP(0.20f).build(),
    };

    public ManaWell(int level) {
        super(MANA_WELL_NAME, skillEffects.length - 1, level, skillEffects[level]);
    }

    public ManaWell() {
        this(0);
    }
}
