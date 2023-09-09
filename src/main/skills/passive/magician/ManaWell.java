package main.skills.passive.magician;

import main.exceptions.LevelExceededException;
import main.skills.Skill;
import main.skills.passive.PassiveSkill;
import main.stats.Stats;
import main.stats.StatsBuilder;

import static main.skills.SkillNames.MANA_WELL_NAME;

public class ManaWell extends PassiveSkill {
    private static final Stats[] skillEffects = {
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

    public static final int MAX_LEVEL = skillEffects.length - 1;

    private ManaWell(int level) {
        super(MANA_WELL_NAME, MAX_LEVEL, level, skillEffects);
    }

    public static Skill invoke(int level) throws LevelExceededException {
        if (level > MAX_LEVEL) {
            throw new LevelExceededException();
        }
        return new ManaWell(level);
    }
}
