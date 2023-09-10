package main.ui.screens.menus;

import main.agents.Agent;
import main.agents.character.Character;
import main.agents.monsters.Monster;
import main.services.FileServices;
import main.skills.ActiveSkill;
import main.skills.SupportSkill;
import main.skills.attack.AttackSkill;
import main.ui.Logger;
import main.ui.screens.Screen;
import main.utility.Turn;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

import static main.Config.COMBAT_DISPLAY_DELAY;
import static main.ui.UI.*;

public class CombatScreen extends Screen {
    private final Character character;
    private final Monster monster;
    private PriorityQueue<Turn> battleOrder;

    @Override
    protected void handleScreenLogic() throws InterruptedException {
        battleOrder.add(new Turn(character));
        battleOrder.add(new Turn(monster));

        boolean escape = false;
        while (!escape) {
            assert battleOrder.peek() != null;
            Agent attacker = battleOrder.poll().agent;
            assert battleOrder.peek() != null;
            Agent enemy = battleOrder.peek().agent;

            printDividerLong();
            println(attacker.name + "'s turn", 0);
            printDividerLong();
            if (attacker instanceof Character) {
                escape = new CombatMenu((Character) attacker, (Monster) enemy).show();
                if (escape) {
                    try {
                        FileServices.writeCharacterToFile(character);
                    } catch (IOException e) {
                        Logger.error("Unable to save character data");
                    }
                    // Skip the rest of the battle
                    continue;
                }
            } else {
                ActiveSkill skill = ((Monster) attacker).getSkill();
                if (skill instanceof SupportSkill) {
                    ((SupportSkill) skill).apply(attacker);
                } else {
                    attacker.attack((AttackSkill) skill, enemy);
                }
                newline();
            }

            if (enemy.isDead()) {
                println(enemy.name + " is dead.", COMBAT_DISPLAY_DELAY);
                escape = true;
            }
            updateBattleOrder(attacker.stats.spd.getValue());
            battleOrder.add(new Turn(attacker));
        }
    }

    // ========================================
    //  Helper
    // ========================================
    private void updateBattleOrder(int speed) {
        PriorityQueue<Turn> newBattleOrder = new PriorityQueue<>(new CombatComparator());
        while (!battleOrder.isEmpty()) {
            Turn turn = battleOrder.poll();
            turn.reduceWaitTime(speed);
            newBattleOrder.add(turn);
        }
        this.battleOrder = newBattleOrder;
    }

    // ========================================
    //  Constructor
    // ========================================
    public CombatScreen(Character character, Monster monster) {
        super(false);
        this.character = character;
        this.monster = monster;
        this.battleOrder = new PriorityQueue<>(new CombatComparator());
    }

    private static class CombatComparator implements Comparator<Turn> {
        @Override
        public int compare(Turn turn, Turn turn2) {
            return Float.compare(turn.waitTime, turn2.waitTime);
        }
    }
}
