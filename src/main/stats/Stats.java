package main.stats;

public class Stats {
    // Core attributes
    public Stat str; // Strength
    public Stat wis; // Wisdom

    // Auxiliary attributes
    public Stat maxHP; // Maximum HP
    public Stat maxMP; // Maximum MP
    public Stat avd; // Avoidability
    public Stat acc; // Accuracy
    public Stat spd; // Speed

    // Weapon attributes
    public Stat wAtt; // Weapon attack points
    public Stat mAtt; // Magic attack points
    public Stat wMast; // Weapon mastery

    public Stats(Stat str, Stat wis, Stat maxHP, Stat maxMP, Stat avd, Stat acc, Stat spd, Stat wAtt, Stat mAtt,
                 Stat wMast) {
        this.str = str;
        this.wis = wis;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.avd = avd;
        this.acc = acc;
        this.spd = spd;
        this.wAtt = wAtt;
        this.mAtt = mAtt;
        this.wMast = wMast;
    }

    public Stats(int str, int wis, int maxHP, int maxMP, int avd, int acc, int spd, int wAtt, int mAtt, int wMast) {
        this.str = new Stat(str);
        this.wis = new Stat(wis);
        this.maxHP = new Stat(maxHP);
        this.maxMP = new Stat(maxMP);
        this.avd = new Stat(avd);
        this.acc = new Stat(acc);
        this.spd = new Stat(spd);
        this.wAtt = new Stat(wAtt);
        this.mAtt = new Stat(mAtt);
        this.wMast = new Stat(wMast);
    }

    public Stats add(Stats other) {
        Stats newStats = clone();

        if (other.str != null) {
            newStats.str = newStats.str.add(other.str);
        }
        if (other.wis != null) {
            newStats.wis = newStats.wis.add(other.wis);
        }
        if (other.maxHP != null) {
            newStats.maxHP = newStats.maxHP.add(other.maxHP);
        }
        if (other.maxMP != null) {
            newStats.maxMP = newStats.maxMP.add(other.maxMP);
        }
        if (other.avd != null) {
            newStats.avd = newStats.avd.add(other.avd);
        }
        if (other.acc != null) {
            newStats.acc = newStats.acc.add(other.acc);
        }
        if (other.spd != null) {
            newStats.spd = newStats.spd.add(other.spd);
        }
        if (other.wAtt != null) {
            newStats.wAtt = newStats.wAtt.add(other.wAtt);
        }
        if (other.mAtt != null) {
            newStats.mAtt = newStats.mAtt.add(other.mAtt);
        }
        if (other.wMast != null) {
            newStats.wMast = newStats.wMast.add(other.wMast);
        }
        return newStats;
    }

    public Stats clone() {
        return new Stats(
                this.str.clone(),
                this.wis.clone(),
                this.maxHP.clone(),
                this.maxMP.clone(),
                this.avd.clone(),
                this.acc.clone(),
                this.spd.clone(),
                this.wAtt.clone(),
                this.mAtt.clone(),
                this.wMast.clone()
        );
    }

    public String toFileFormat() {
        return "str=" + str.getBaseValue() + "\n" +
                "wis=" + wis.getBaseValue() + "\n" +
                "maxHP=" + maxHP.getBaseValue() + "\n" +
                "maxMP=" + maxMP.getBaseValue() + "\n" +
                "avd=" + avd.getBaseValue() + "\n" +
                "acc=" + acc.getBaseValue() + "\n" +
                "spd=" + spd.getBaseValue() + "\n" +
                "wAtt=" + wAtt.getBaseValue() + "\n" +
                "mAtt=" + mAtt.getBaseValue() + "\n" +
                "wMast=" + wMast.getBaseValue() + "\n";
    }
}
