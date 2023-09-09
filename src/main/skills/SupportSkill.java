package main.skills;

import main.agents.Character;

public interface SupportSkill {
    void apply(Character character);

    void unapply(Character character); // For leveling up and buffs
}
