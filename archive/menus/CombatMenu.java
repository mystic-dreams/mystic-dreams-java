package main.ui.menus;

import main.agents.character.Character;
import main.agents.monsters.Monster;
import main.skills.ActiveSkill;
import main.skills.Skill;
import main.skills.SupportSkill;
import main.skills.attack.AttackSkill;
import main.ui.Logger;

import java.util.Arrays;

public class CombatMenu extends Menu {
    private final Character character;
    private final Monster monster;

    public CombatMenu(Character character, Monster monster) {
        super(null, "Choose a skill:", getSkillNames(character.skillBook.getActiveSkills()));
        this.character = character;
        this.monster = monster;
    }

    @Override
    protected boolean handleSelection(int selection) throws InterruptedException {
        ActiveSkill skill = (ActiveSkill) character.getSkill(options[selection - 1]);
        if (skill == null) {
            Logger.error(options[selection - 1] + " is not a valid skill");
            return true;
        }

        if (skill instanceof SupportSkill) {
            ((SupportSkill) skill).apply(character);
        } else {
            character.attack((AttackSkill) skill, monster);
        }
        return true;
    }

    private static String[] getSkillNames(Skill[] skills) {
        return Arrays.stream(skills).map(skill -> skill.name).toList().toArray(new String[0]);
    }
}
