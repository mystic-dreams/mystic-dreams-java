package main.agents.character;

import main.agents.Agent;
import main.exceptions.LevelExceededException;
import main.services.Savable;
import main.skills.Skill;
import main.skills.passive.PassiveSkill;
import main.skills.skillbooks.MagicianSkillBook;
import main.skills.skillbooks.SkillBook;
import main.skills.skillbooks.WarriorSkillBook;
import main.stats.StatType;
import main.stats.Stats;
import main.stats.StatsBuilder;

import java.io.IOException;

import static main.Config.*;
import static main.Constants.*;
import static main.services.FileServices.writeCharacterToFile;
import static main.stats.DefaultStats.MAGICIAN_STARTER_STATS;
import static main.stats.DefaultStats.WARRIOR_STARTER_STATS;
import static main.ui.UI.notice;
import static main.ui.UI.println;

public class Character extends Agent implements Savable {
    public final JobClass job;

    private int ap; // Attribute points
    private int sp; // Skill points


    public Character(String name, int level, int hp, int mp, Stats stats, JobClass job, SkillBook skillBook, int ap,
                     int sp, int exp) {
        super(name, hp, mp, level, exp, stats, skillBook);
        this.job = job;
        this.ap = ap;
        this.sp = sp;

        for (PassiveSkill skill : this.skillBook.getPassiveSkills()) {
            useSkill(skill);
        }

        this.hp = hp == 0 ? this.stats.maxHP.getValue() : Math.min(hp, this.stats.maxHP.getValue());
        this.mp = mp == 0 ? this.stats.maxMP.getValue() : Math.min(mp, this.stats.maxMP.getValue());
    }

    public Character(String name, JobClass job) {
        this(name, 1, 0, 0, getStarterStats(job), job, getBasicSkillBook(job), 0, 0, 0);
    }

    // ========================================
    //  Stats
    // ========================================
    private static Stats getStarterStats(JobClass job) {
        return job == JobClass.WARRIOR ? WARRIOR_STARTER_STATS : MAGICIAN_STARTER_STATS;
    }


    // ========================================
    //  Skills
    // ========================================

    public void levelSkill(String skillName, int levels) throws InterruptedException {

        if (levels > sp) {
            notice("Insufficient SP");
            return;
        }

        Skill skill = skillBook.getSkill(skillName);
        try {
            // Note: Buffs will not be refreshed. Stats will be updated on next cast.
            if (skill instanceof PassiveSkill) {
                ((PassiveSkill) skill).unapply(this);
                skill.levelUp(levels);
            }
        } catch (LevelExceededException e) {
            notice("Exceeded max level");
        } finally {
            // Apply back to stats regardless of success. Old stats will be reinstated on failure.
            if (skill instanceof PassiveSkill) {
                ((PassiveSkill) skill).apply(this);
            }
        }
    }

    private static SkillBook getBasicSkillBook(JobClass job) {
        return job == JobClass.WARRIOR ? WarriorSkillBook.init() : MagicianSkillBook.init();
    }

    // ========================================
    //  EXP and Level
    // ========================================

    public void gainExp(int earnedExp) throws InterruptedException, IOException {
        println("Earned " + earnedExp + " experience");
        exp += earnedExp;
        while (exp >= getExpRequirement(level)) {
            levelUp();
        }
        writeCharacterToFile(this);
    }

    private void levelUp() throws InterruptedException {
        level += 1;
        Stats upgradeStats = new StatsBuilder()
                .setMaxHP(getHPIncrementOnLevel(stats.str.getBaseValue(), stats.maxHP.getBaseValue()), StatType.BASE)
                .setMaxMP(getMPIncrementOnLevel(stats.wis.getBaseValue(), stats.maxMP.getBaseValue()), StatType.BASE)
                .build();
        this.stats = this.stats.add(upgradeStats);
        recover();
        ap += AP_PER_LEVEL;
        sp += SP_PER_LEVEL;
        notice("LEVEL UP!");
    }

    // ========================================
    //  Helper
    // ========================================
    @Override
    public String toFileFormat() {
        return NAME_FIELD + "=" + name + "\n"
                + LEVEL_FIELD + "=" + level + "\n"
                + JOB_FIELD + "=" + job.value + "\n"
                + HP_FIELD + "=" + hp + "\n"
                + MP_FIELD + "=" + mp + "\n"
                + stats.toFileFormat()
                + AP_FIELD + "=" + ap + "\n"
                + SP_FIELD + "=" + sp + "\n"
                + EXP_FIELD + "=" + exp + "\n";
    }
}
