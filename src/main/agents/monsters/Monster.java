package main.agents.monsters;

import main.agents.Agent;
import main.skills.ActiveSkill;
import main.skills.skillbooks.SkillBook;
import main.stats.Stats;

public abstract class Monster extends Agent {

    protected Monster(String name, int hp, int mp, int level, int exp, Stats stats, SkillBook skillBook) {
        super(name, hp, mp, level, exp, stats, skillBook);
    }

    public ActiveSkill getSkill() {
        ActiveSkill[] skills = skillBook.getActiveSkills();

        return skills[rand.nextInt(skills.length)];
    }
}
