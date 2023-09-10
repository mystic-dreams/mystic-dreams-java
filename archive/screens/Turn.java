package main.ui.screens;

import main.agents.Agent;

public class Turn {
    public final Agent agent;
    public float waitTime;


    public Turn(Agent agent) {
        this.agent = agent;
        this.waitTime = 1f / agent.stats.spd.getValue();
    }

    public void reduceWaitTime(int speed) {
        if (speed == 0) {
            return;
        }
        waitTime -= 1f / speed;
    }
}
