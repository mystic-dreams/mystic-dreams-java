package main.skills.passive;

import main.stats.Stat;
import main.stats.Stats;

public class ManaWell extends PassiveSkill {
    private static final Stats[] skillEffects = {
            new Stats(null, null, null, new Stat(0, 0, 0.00f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.01f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.02f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.03f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.04f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.05f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.06f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.07f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.08f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.09f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.10f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.11f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.12f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.13f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.14f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.15f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.16f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.17f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.18f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.19f), null, null, null, null, null, null),
            new Stats(null, null, null, new Stat(0, 0, 0.20f), null, null, null, null, null, null),
    };

    public ManaWell(int level) {
        super("Mana Well", skillEffects.length - 1, level, skillEffects[level]);
    }

    public ManaWell() {
        this(0);
    }
}
