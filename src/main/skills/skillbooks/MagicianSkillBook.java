package main.skills.skillbooks;

import main.skills.active.attack.magician.ManaBolt;
import main.skills.active.support.ManaArmor;
import main.skills.passive.ManaWell;

public class MagicianSkillBook extends SkillBook {
    public MagicianSkillBook() throws InterruptedException {
        super(new ManaWell(20), new ManaBolt(20), new ManaArmor(10));
    }
}
