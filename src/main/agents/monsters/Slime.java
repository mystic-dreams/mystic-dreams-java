package main.agents.monsters;

import main.skills.attack.BasicAttack;
import main.skills.skillbooks.SkillBook;
import main.stats.Stats;

import static main.stats.DefaultStats.BASE_CRIT_DMG;
import static main.stats.DefaultStats.BASE_WMAST;

public class Slime extends Monster {
    private static final int maxHP = 50;
    private static final int maxMP = 50;

    public Slime() {
        super("Slime", maxHP, maxMP, 1, 3, new Stats(
                0, 0, maxHP, maxMP, 10, 5, 20, 10, 8, 5, BASE_CRIT_DMG, 2, 1, BASE_WMAST
        ), new SkillBook(new BasicAttack()));
    }
}
