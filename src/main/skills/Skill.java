package main.skills;

import main.exceptions.LevelExceededException;

public abstract class Skill {
    public final String name;
    public final int maxLevel;
    public int level;

    protected Skill(String name, int maxLevel, int level) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.level = Math.min(level, maxLevel);
    }

    public void levelUp(int levels) throws LevelExceededException {
        if (maxLevel < level + levels) {
            throw new LevelExceededException();
        }
        this.level += levels;
    }

    public void levelUp() throws LevelExceededException {
        levelUp(1);
    }
}
