package main.skills.active.support;

import main.skills.active.ActiveSkill;
import main.stats.Stats;

public class BuffSkill extends ActiveSkill {

    public final Stats effect;
    public final int duration; // In milliseconds


    public BuffSkill(String name, int maxLevel, int level, int hpConsumption, int mpConsumption, Stats effect,
                     int duration) {
        super(name, maxLevel, level, hpConsumption, mpConsumption);
        this.effect = effect;
        this.duration = duration;
    }
}
