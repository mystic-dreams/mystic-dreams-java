package main.skills.passive;

import main.skills.Skill;
import main.stats.Stats;

public abstract class PassiveSkill extends Skill {
    public final Stats effect;

    public PassiveSkill(String name, int maxLevel, int level, Stats effect) {
        super(name, maxLevel, level);
        this.effect = effect;
    }

}
