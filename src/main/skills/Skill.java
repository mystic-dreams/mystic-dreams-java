package main.skills;

public abstract class Skill {
    public final String name;
    public final int maxLevel;
    public final int level;

    public Skill(String name, int maxLevel, int level) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.level = level;
    }
}
