package main.agents;

import main.skills.Skill;
import main.skills.passive.PassiveSkill;
import main.skills.skillbooks.MagicianSkillBook;
import main.skills.skillbooks.SkillBook;
import main.stats.Stats;

import static main.stats.DefaultStats.MAGICIAN_STARTER_STATS;
import static main.stats.DefaultStats.WARRIOR_STARTER_STATS;

public class Character {
    public final String name;
    public final int level;

    public final JobClass job;

    private int hp;
    private int mp;
    private int ap; // Attribute points
    private int sp; // Skill points

    private Stats stats;

    private SkillBook skillBook;

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
            this.stats = this.stats.add(((PassiveSkill) skill).effect);
        }
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

    public String toFileFormat() {
        return "name=" + name + "\n" +
                "level=" + level + "\n" +
                "job=" + job.value + "\n" +
                "hp=" + hp + "\n" +
                "mp=" + mp + "\n" +
                stats.toFileFormat() +
                "ap=" + ap + "\n" +
                "sp=" + sp + "\n";
    }

    private static Stats getStarterStats(JobClass job) {
        return job == JobClass.WARRIOR ? WARRIOR_STARTER_STATS : MAGICIAN_STARTER_STATS;
    }
}
