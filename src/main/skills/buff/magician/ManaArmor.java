package main.skills.buff.magician;

import main.exceptions.LevelExceededException;
import main.skills.Skill;
import main.skills.buff.BuffSkill;
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
    private static final int[] durations = {0, 3 * MS_PER_MIN, 3 * MS_PER_MIN, 3 * MS_PER_MIN, 3 * MS_PER_MIN,
            3 * MS_PER_MIN, 3 * MS_PER_MIN, 3 * MS_PER_MIN, 3 * MS_PER_MIN, 3 * MS_PER_MIN, 3 * MS_PER_MIN};
    public static final int MAX_LEVEL = effects.length - 1;

    private ManaArmor(int level) {
        super(MANA_ARMOR_NAME, MAX_LEVEL, level, null, mpConsumptions, effects, durations);
    }

    public static Skill invoke(int level) throws LevelExceededException {
        if (level > MAX_LEVEL) {
            throw new LevelExceededException();
        }
        return new ManaArmor(level);
    }
}
