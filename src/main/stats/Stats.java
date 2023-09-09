package main.stats;

import main.services.Savable;

import static main.Constants.*;

/**
 * Changing of fields requires updating of the following:
 * - Stats: toFileFormat()
 * - DefaultStats: Various jobs' default stats
 * - StatsBuilder: Update fields and setters
 * - FileServices: loadCharacter()
 * - CharacterStatsScreen: Update stats displayed
 */
public class Stats implements Savable {
    // Core attributes
    public Stat str; // Strength
    public Stat wis; // Wisdom

    // Auxiliary attributes
    public Stat maxHP; // Maximum HP
    public Stat maxMP; // Maximum MP

    public Stat wDef; // Weapon Defence

    public Stat mDef; // Magic Defence
    public Stat avd; // Avoidability
    public Stat acc; // Accuracy
    public Stat spd; // Speed
    public Stat critRate; // Critical Rate
    public Stat critDmg; // Critical Damage

    // Weapon attributes
    public Stat wAtt; // Weapon attack points
    public Stat mAtt; // Magic attack points
    public Stat wMast; // Weapon mastery

    public Stats(Stat str, Stat wis, Stat maxHP, Stat maxMP, Stat wDef, Stat mDef, Stat avd, Stat acc, Stat spd,
                 Stat critRate, Stat critDmg, Stat wAtt, Stat mAtt, Stat wMast) {
        this.str = str;
        this.wis = wis;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.wDef = wDef;
        this.mDef = mDef;
        this.avd = avd;
        this.acc = acc;
        this.spd = spd;
        this.critRate = critRate;
        this.critDmg = critDmg;
        this.wAtt = wAtt;
        this.mAtt = mAtt;
        this.wMast = wMast;
    }

    public Stats(int str, int wis, int maxHP, int maxMP, int wDef, int mDef, int avd, int acc, int spd, int critRate,
                 int critDmg, int wAtt, int mAtt, int wMast) {
        this.str = new Stat(str);
        this.wis = new Stat(wis);
        this.maxHP = new Stat(maxHP);
        this.maxMP = new Stat(maxMP);
        this.wDef = new Stat(wDef);
        this.mDef = new Stat(mDef);
        this.avd = new Stat(avd);
        this.acc = new Stat(acc);
        this.spd = new Stat(spd);
        this.critRate = new Stat(critRate);
        this.critDmg = new Stat(critDmg);
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
        if (other.wDef != null) {
            newStats.wDef = newStats.wDef.add(other.wDef);
        }
        if (other.mDef != null) {
            newStats.mDef = newStats.mDef.add(other.mDef);
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
        if (other.critRate != null) {
            newStats.critRate = newStats.critRate.add(other.critRate);
        }
        if (other.critDmg != null) {
            newStats.critDmg = newStats.critDmg.add(other.critDmg);
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

    public Stats remove(Stats other) {
        Stats newStats = clone();

        if (other.str != null) {
            newStats.str = newStats.str.remove(other.str);
        }
        if (other.wis != null) {
            newStats.wis = newStats.wis.remove(other.wis);
        }
        if (other.maxHP != null) {
            newStats.maxHP = newStats.maxHP.remove(other.maxHP);
        }
        if (other.maxMP != null) {
            newStats.maxMP = newStats.maxMP.remove(other.maxMP);
        }
        if (other.wDef != null) {
            newStats.wDef = newStats.wDef.remove(other.wDef);
        }
        if (other.mDef != null) {
            newStats.mDef = newStats.mDef.remove(other.mDef);
        }
        if (other.avd != null) {
            newStats.avd = newStats.avd.remove(other.avd);
        }
        if (other.acc != null) {
            newStats.acc = newStats.acc.remove(other.acc);
        }
        if (other.spd != null) {
            newStats.spd = newStats.spd.remove(other.spd);
        }
        if (other.critRate != null) {
            newStats.critRate = newStats.critRate.remove(other.critRate);
        }
        if (other.critDmg != null) {
            newStats.critDmg = newStats.critDmg.remove(other.critDmg);
        }
        if (other.wAtt != null) {
            newStats.wAtt = newStats.wAtt.remove(other.wAtt);
        }
        if (other.mAtt != null) {
            newStats.mAtt = newStats.mAtt.remove(other.mAtt);
        }
        if (other.wMast != null) {
            newStats.wMast = newStats.wMast.remove(other.wMast);
        }
        return newStats;
    }

    public Stats clone() {
        return new Stats(
                this.str.clone(),
                this.wis.clone(),
                this.maxHP.clone(),
                this.maxMP.clone(),
                this.wDef.clone(),
                this.mDef.clone(),
                this.avd.clone(),
                this.acc.clone(),
                this.spd.clone(),
                this.critRate.clone(),
                this.critDmg.clone(),
                this.wAtt.clone(),
                this.mAtt.clone(),
                this.wMast.clone()
        );
    }

    @Override
    public String toFileFormat() {
        return STR_STAT_FIELD + "=" + str.getBaseValue() + "\n" +
                WIS_STAT_FIELD + "=" + wis.getBaseValue() + "\n" +
                MAX_HP_STAT_FIELD + "=" + maxHP.getBaseValue() + "\n" +
                MAX_MP_STAT_FIELD + "=" + maxMP.getBaseValue() + "\n" +
                WDEF_STAT_FIELD + "=" + wDef.getBaseValue() + "\n" +
                MDEF_STAT_FIELD + "=" + mDef.getBaseValue() + "\n" +
                AVD_STAT_FIELD + "=" + avd.getBaseValue() + "\n" +
                ACC_STAT_FIELD + "=" + acc.getBaseValue() + "\n" +
                SPD_STAT_FIELD + "=" + spd.getBaseValue() + "\n" +
                CRIT_RATE_STAT_FIELD + "=" + critRate.getBaseValue() + "\n" +
                CRIT_DMG_STAT_FIELD + "=" + critDmg.getBaseValue() + "\n" +
                WATT_STAT_FIELD + "=" + wAtt.getBaseValue() + "\n" +
                MATT_STAT_FIELD + "=" + mAtt.getBaseValue() + "\n" +
                WMAST_STAT_FIELD + "=" + wMast.getBaseValue() + "\n";
    }
}
