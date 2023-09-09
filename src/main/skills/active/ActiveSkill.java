package main.skills.active;

import main.skills.Skill;

public abstract class ActiveSkill extends Skill {
    public final int hpConsumption;

    public final int mpConsumption;

    public ActiveSkill(String name, int maxLevel, int level, int hpConsumption, int mpConsumption) {
        super(name, maxLevel, level);
        this.hpConsumption = hpConsumption;
        this.mpConsumption = mpConsumption;
    }
}
