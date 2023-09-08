package main.skills.skillbooks;

import main.skills.passive.ManaWell;

public class MagicianSkillBook extends SkillBook {
    public MagicianSkillBook() throws InterruptedException {
        super(new ManaWell());
    }
}
