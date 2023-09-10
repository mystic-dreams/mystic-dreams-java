package main.ui.screens.menus;

import main.agents.character.Character;
import main.agents.monsters.Monster;
import main.skills.ActiveSkill;
import main.skills.Skill;
import main.skills.SupportSkill;
import main.skills.attack.AttackSkill;
import main.ui.Logger;

import java.util.Arrays;

public class SkillCombatMenu extends Menu {
    private final Character character;
    private final Monster monster;


    @Override
    boolean handleSelection(int selection) throws InterruptedException {
        ActiveSkill skill = (ActiveSkill) character.getSkill(options[selection - 1]);
        if (skill == null) {
            Logger.error(options[selection - 1] + " is not a valid skill");
            return false;
        }

        if (skill instanceof SupportSkill) {
            ((SupportSkill) skill).apply(character);
        } else {
            character.attack((AttackSkill) skill, monster);
        }
        skipPreviousScreen = true;
        return true;
    }

    // ========================================
    //  Helper
    // ========================================
    private static String[] getSkillNames(Skill[] skills) {
        return Arrays.stream(skills).map(skill -> skill.name).toList().toArray(new String[0]);
    }

    // ========================================
    //  Constructor
    // ========================================
    protected SkillCombatMenu(Character character, Monster monster) {
        // Previous screen should be action selection. Once attack finish, should skip action selection.
        super("Choose a skill:", false, getSkillNames(character.skillBook.getActiveSkills()));
        this.character = character;
        this.monster = monster;
    }
}
