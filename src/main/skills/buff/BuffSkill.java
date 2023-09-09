package main.skills.buff;

import main.agents.Character;
import main.skills.ActiveSkill;
import main.skills.SupportSkill;
import main.stats.Stats;
import main.ui.Logger;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BuffSkill extends ActiveSkill implements SupportSkill {

    public final Stats[] effects;
    public final int[] durations; // In milliseconds


    protected BuffSkill(String name, int maxLevel, int level, int[] hpConsumptions, int[] mpConsumptions,
                        Stats[] effects, int[] durations) {
        super(name, maxLevel, level, hpConsumptions, mpConsumptions);
        this.effects = effects;
        this.durations = durations;
    }

    @Override
    public void apply(Character character) {
        Logger.debug("Using buff skill " + name);
        character.stats = character.stats.add(effects[level]);

        TimerTask task = new TimerTask() {
            public void run() {
                Logger.debug("Timer expired for " + name);
                Logger.debug("Removing stats");
                unapply(character);
            }
        };
        Timer timer = new Timer("Buff");
        timer.schedule(task, durations[level]);
    }

    @Override
    public void unapply(Character character) {
        character.stats = character.stats.remove(effects[level]);
    }
}
