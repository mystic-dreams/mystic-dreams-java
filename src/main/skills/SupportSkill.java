package main.skills;

import main.agents.Agent;

public interface SupportSkill {
    void apply(Agent character);

    void unapply(Agent character); // For leveling up and buffs
}
