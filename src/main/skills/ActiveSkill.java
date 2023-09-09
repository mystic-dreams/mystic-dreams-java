package main.skills;

public abstract class ActiveSkill extends Skill {
    public final int[] hpConsumption;

    public final int[] mpConsumption;

    protected ActiveSkill(String name, int maxLevel, int level, int[] hpConsumption, int[] mpConsumption) {
        super(name, maxLevel, level);
        this.hpConsumption = hpConsumption;
        this.mpConsumption = mpConsumption;
    }
}
