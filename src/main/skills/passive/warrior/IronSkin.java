package main.skills.passive.warrior;

import main.exceptions.LevelExceededException;
import main.skills.Skill;
import main.skills.passive.PassiveSkill;
import main.stats.Stats;
import main.stats.StatsBuilder;

import static main.skills.SkillNames.IRON_SKIN_NAME;

public class IronSkin extends PassiveSkill {

    private static final Stats[] skillEffects = {
            new StatsBuilder().setWDef(0.00f).build(),
            new StatsBuilder().setWDef(0.01f).build(),
            new StatsBuilder().setWDef(0.02f).build(),
            new StatsBuilder().setWDef(0.03f).build(),
            new StatsBuilder().setWDef(0.04f).build(),
            new StatsBuilder().setWDef(0.05f).build(),
            new StatsBuilder().setWDef(0.06f).build(),
            new StatsBuilder().setWDef(0.07f).build(),
            new StatsBuilder().setWDef(0.08f).build(),
            new StatsBuilder().setWDef(0.09f).build(),
            new StatsBuilder().setWDef(0.10f).build(),
            new StatsBuilder().setWDef(0.11f).build(),
            new StatsBuilder().setWDef(0.12f).build(),
            new StatsBuilder().setWDef(0.13f).build(),
            new StatsBuilder().setWDef(0.14f).build(),
            new StatsBuilder().setWDef(0.15f).build(),
            new StatsBuilder().setWDef(0.16f).build(),
            new StatsBuilder().setWDef(0.17f).build(),
            new StatsBuilder().setWDef(0.18f).build(),
            new StatsBuilder().setWDef(0.19f).build(),
            new StatsBuilder().setWDef(0.20f).build(),
    };

    public static final int MAX_LEVEL = skillEffects.length - 1;

    private IronSkin(int level) {
        super(IRON_SKIN_NAME, MAX_LEVEL, level, skillEffects);
    }

    public static Skill invoke(int level) throws LevelExceededException {
        if (level > MAX_LEVEL) {
            throw new LevelExceededException();
        }
        return new IronSkin(level);
    }
}
