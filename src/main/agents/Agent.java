package main.agents;

import main.skills.AttackType;
import main.skills.Skill;
import main.skills.SupportSkill;
import main.skills.attack.AttackSkill;
import main.skills.skillbooks.SkillBook;
import main.stats.Stats;

import java.util.Random;

import static main.Config.COMBAT_DISPLAY_DELAY;
import static main.Config.OVERPOWER_LEVEL_THRESHOLD;
import static main.ui.UI.println;

public abstract class Agent {
    public final String name;
    public Stats stats;
    public SkillBook skillBook;

    protected int level;
    protected int hp;
    protected int mp;
    protected int exp;

    protected final Random rand = new Random();

    protected Agent(String name, int hp, int mp, int level, int exp, Stats stats, SkillBook skillBook) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.level = level;
        this.exp = exp;
        this.stats = stats;
        this.skillBook = skillBook;
    }

    // ========================================
    //  Stats
    // ========================================

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getHP() {
        return hp;
    }

    public int getMP() {
        return mp;
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

    // ========================================
    //  Skills
    // ========================================

    protected void useSkill(SupportSkill skill) {
        skill.apply(this);
    }

    public Skill getSkill(String skillName) {
        return skillBook.getSkill(skillName);
    }

    // ========================================
    //  Combat
    // ========================================

    public void attack(AttackSkill skill, Agent enemy) throws InterruptedException {
        println(name + " uses " + skill.name, COMBAT_DISPLAY_DELAY);

        int accuracyPoint = rand.nextInt(stats.acc.getValue() + 1);
        int avoidabilityPoint = rand.nextInt(enemy.stats.avd.getValue() + 1);

        if (accuracyPoint < avoidabilityPoint) {
            println("Attack Missed!", COMBAT_DISPLAY_DELAY);
            return;
        }

        long damage = 1;
        // If enemy is 20 levels or above, damage will only be 1;
        if (enemy.level - level < OVERPOWER_LEVEL_THRESHOLD) {
            damage = skill.calculateDamage(this.stats);
        }
        enemy.receiveDamage(damage, skill.attackType);
    }

    public void receiveDamage(long damage, AttackType type) throws InterruptedException {
        int def = type == AttackType.PHYSICAL ? stats.wDef.getValue() : stats.mDef.getValue();
        long effectiveDamage = Math.max(damage - def, 1); // Minimum allowed damage is 1.
        println(name + " receives " + effectiveDamage + " damage", COMBAT_DISPLAY_DELAY);
        this.hp = (int) Math.max(hp - effectiveDamage, 0);
    }

    public boolean isDead() {
        return hp == 0;
    }

}
