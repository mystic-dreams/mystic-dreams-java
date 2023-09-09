package main.skills.passive;

import main.agents.Agent;
import main.skills.Skill;
import main.skills.SupportSkill;
import main.stats.Stats;

public abstract class PassiveSkill extends Skill implements SupportSkill {
    public final Stats[] effects;

    protected PassiveSkill(String name, int maxLevel, int level, Stats[] effects) {
        super(name, maxLevel, level);
        this.effects = effects;
    }

    @Override
    public void apply(Agent agent) {
        agent.stats = agent.stats.add(effects[level]);
    }

    @Override
    public void unapply(Agent agent) {
        agent.stats = agent.stats.remove(effects[level]);
    }
}
