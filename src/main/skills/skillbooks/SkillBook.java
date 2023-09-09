package main.skills.skillbooks;

import main.services.Savable;
import main.skills.Skill;
import main.skills.passive.PassiveSkill;
import main.ui.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SkillBook implements Savable {
    protected Map<String, Skill> activeSkills = new HashMap<>();
    protected Map<String, Skill> passiveSkills = new HashMap<>();

    public SkillBook(Skill... skills) {
        for (Skill skill : skills) {
            if (skill instanceof PassiveSkill) {
                Logger.debug(skill.name + " added to passive skills");
                this.passiveSkills.put(skill.name, skill);
            } else {
                Logger.debug(skill.name + " added to active skills");
                this.activeSkills.put(skill.name, skill);
            }
        }
    }

    public Skill getSkill(String skillName) {
        Skill skill = activeSkills.get(skillName);
        if (skill == null) {
            skill = passiveSkills.get(skillName);
        }
        return skill;
    }

    public Skill[] getPassiveSkills() {
        return passiveSkills.values().toArray(new Skill[0]);
    }

    public String[] listSkills() {
        Set<String> skills = activeSkills.keySet();
        skills.addAll(passiveSkills.keySet());
        return skills.toArray(new String[]{});
    }

    @Override
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        activeSkills.forEach((name, skill) -> sb.append(name).append("=").append(skill.level).append("\n"));
        passiveSkills.forEach((name, skill) -> sb.append(name).append("=").append(skill.level).append("\n"));
        return sb.toString();
    }
}
