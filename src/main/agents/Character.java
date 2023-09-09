package main.agents;

import main.services.Savable;
import main.skills.Skill;
import main.skills.active.support.BuffSkill;
import main.skills.passive.PassiveSkill;
import main.skills.skillbooks.MagicianSkillBook;
import main.skills.skillbooks.SkillBook;
import main.stats.Stats;
import main.ui.Logger;

import java.util.Timer;
import java.util.TimerTask;

import static main.Constants.*;
import static main.stats.DefaultStats.MAGICIAN_STARTER_STATS;
import static main.stats.DefaultStats.WARRIOR_STARTER_STATS;

public class Character implements Savable {
    public final String name;
    public final int level;

    public final JobClass job;

    private int hp;
    private int mp;
    private int ap; // Attribute points
    private int sp; // Skill points

    private Stats stats;

    public SkillBook skillBook;

    public Character(String name, int level, int hp, int mp, Stats stats, JobClass job, int ap, int sp) throws InterruptedException {
        this.name = name;
        this.level = level;
        this.stats = stats;
        this.job = job;
        this.ap = ap;
        this.sp = sp;

        this.skillBook = new MagicianSkillBook();
        for (Skill skill : this.skillBook.getPassiveSkills()) {
            useSkill(skill);
        }

        this.hp = hp == 0 ? this.stats.maxHP.getValue() : Math.min(hp, this.stats.maxHP.getValue());
        this.mp = mp == 0 ? this.stats.maxMP.getValue() : Math.min(mp, this.stats.maxMP.getValue());
    }

    public Character(String name, JobClass job) throws InterruptedException {
        this(name, 1, 0, 0, getStarterStats(job), job, 0, 0);

    }

    public void useSkill(Skill skill) {
        if (skill instanceof PassiveSkill) {
            Logger.debug("Using passive skill " + skill.name);
            this.stats = this.stats.add(((PassiveSkill) skill).effect);
        } else if (skill instanceof BuffSkill) {
            Logger.debug("Using buff skill " + skill.name);
            this.stats = this.stats.add(((BuffSkill) skill).effect);

            TimerTask task = new TimerTask() {
                public void run() {
                    Logger.debug("Timer expired for " + skill.name);
                    Logger.debug("Removing stats");
                    stats = stats.remove(((BuffSkill) skill).effect);
                }
            };
            Timer timer = new Timer("Buff");
            timer.schedule(task, ((BuffSkill) skill).duration);
        }
    }

    public void recover() {
        regenerate();
        rejuvenate();
    }

    public void regenerate() {
        this.hp = stats.maxHP.getValue();
    }

    public void rejuvenate() {
        this.mp = stats.maxMP.getValue();
    }

    public int getHP() {
        return hp;
    }

    public int getMP() {
        return mp;
    }

    public Stats getStats() {
        return stats.clone();
    }

    // ========================================
    //  Helper
    // ========================================
    @Override
    public String toFileFormat() {
        return NAME_FIELD + "=" + name + "\n" +
                LEVEL_FIELD + "=" + level + "\n" +
                JOB_FIELD + "=" + job.value + "\n" +
                HP_FIELD + "=" + hp + "\n" +
                MP_FIELD + "=" + mp + "\n" +
                stats.toFileFormat() +
                AP_FIELD + "=" + ap + "\n" +
                SP_FIELD + "=" + sp + "\n";
    }

    private static Stats getStarterStats(JobClass job) {
        return job == JobClass.WARRIOR ? WARRIOR_STARTER_STATS : MAGICIAN_STARTER_STATS;
    }
}
