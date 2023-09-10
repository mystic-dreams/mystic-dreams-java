package main.ui.screens;

import main.agents.Agent;
import main.agents.character.Character;
import main.agents.monsters.Monster;
import main.skills.ActiveSkill;
import main.skills.SupportSkill;
import main.skills.attack.AttackSkill;
import main.ui.menus.CombatMoveMenu;

import java.util.Comparator;
import java.util.PriorityQueue;

import static main.Config.COMBAT_DISPLAY_DELAY;
import static main.ui.UI.println;

public class CombatScreen extends Screen {

    private final Character character;
    private final Monster monster;

    private PriorityQueue<Turn> battleOrder;

    public CombatScreen(Character character, Monster monster) {
        super(false);
        this.character = character;
        this.monster = monster;
        this.battleOrder = new PriorityQueue<>(new CombatComparator());
    }

    public boolean show() throws InterruptedException {
        battleOrder.add(new Turn(character));
        battleOrder.add(new Turn(monster));
        while (true) {
            assert battleOrder.peek() != null;
            Agent attacker = battleOrder.poll().agent;
            assert battleOrder.peek() != null;
            Agent enemy = battleOrder.peek().agent;

            if (attacker instanceof Character) {
                new CombatMoveMenu((Character) attacker, (Monster) enemy).show();
            } else {
                ActiveSkill skill = ((Monster) attacker).getSkill();
                if (skill instanceof SupportSkill) {
                    ((SupportSkill) skill).apply(attacker);
                } else {
                    attacker.attack((AttackSkill) skill, enemy);
                }
            }

            if (enemy.isDead()) {
                println(enemy.name + " is dead.", COMBAT_DISPLAY_DELAY);
                break;
            }
            updateBattleOrder(attacker.stats.spd.getValue());
            battleOrder.add(new Turn(attacker));
        }
        return skipPreviousScreen;
    }

    private void updateBattleOrder(int speed) {
        PriorityQueue<Turn> newBattleOrder = new PriorityQueue<>(new CombatComparator());
        while (!battleOrder.isEmpty()) {
            Turn turn = battleOrder.poll();
            turn.reduceWaitTime(speed);
            newBattleOrder.add(turn);
        }
        this.battleOrder = newBattleOrder;
    }

    private static class CombatComparator implements Comparator<Turn> {
        @Override
        public int compare(Turn turn, Turn turn2) {
            return Float.compare(turn.waitTime, turn2.waitTime);
        }
    }
}
