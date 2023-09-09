package main.stats;

public class StatsBuilder {
    private Stat str = null;
    private Stat wis = null;
    private Stat maxHP = null;
    private Stat maxMP = null;
    private Stat wDef = null;
    private Stat mDef = null;
    private Stat avd = null;
    private Stat acc = null;
    private Stat spd = null;
    private Stat critRate = null;
    private Stat critDmg = null;
    private Stat wAtt = null;
    private Stat mAtt = null;
    private Stat wMast = null;

    public StatsBuilder setStr(Stat strStat) {
        this.str = strStat;
        return this;
    }

    public StatsBuilder setStr(int strStat, StatType type) {
        this.str = type == StatType.BASE ? new Stat(strStat, 0, 0) : new Stat(0, strStat, 0);
        return this;
    }

    public StatsBuilder setStr(float strMul) {
        this.str = new Stat(0, 0, strMul);
        return this;
    }

    public StatsBuilder setWis(Stat wisStat) {
        this.wis = wisStat;
        return this;
    }

    public StatsBuilder setWis(int wisStat, StatType type) {
        this.wis = type == StatType.BASE ? new Stat(wisStat, 0, 0) : new Stat(0, wisStat, 0);
        return this;
    }

    public StatsBuilder setWis(float wisMul) {
        this.wis = new Stat(0, 0, wisMul);
        return this;
    }

    public StatsBuilder setMaxHP(Stat maxHPStat) {
        this.maxHP = maxHPStat;
        return this;
    }

    public StatsBuilder setMaxHP(int maxHPStat, StatType type) {
        this.maxHP = type == StatType.BASE ? new Stat(maxHPStat, 0, 0) : new Stat(0, maxHPStat, 0);
        return this;
    }

    public StatsBuilder setMaxHP(float maxHPMul) {
        this.maxHP = new Stat(0, 0, maxHPMul);
        return this;
    }

    public StatsBuilder setMaxMP(Stat maxMPStat) {
        this.maxMP = maxMPStat;
        return this;
    }

    public StatsBuilder setMaxMP(int maxMPStat, StatType type) {
        this.maxMP = type == StatType.BASE ? new Stat(maxMPStat, 0, 0) : new Stat(0, maxMPStat, 0);
        return this;
    }

    public StatsBuilder setMaxMP(float maxMPMul) {
        this.maxMP = new Stat(0, 0, maxMPMul);
        return this;
    }

    public StatsBuilder setWDef(Stat wDefStat) {
        this.wDef = wDefStat;
        return this;
    }

    public StatsBuilder setWDef(int wDefStat, StatType type) {
        this.wDef = type == StatType.BASE ? new Stat(wDefStat, 0, 0) : new Stat(0, wDefStat, 0);
        return this;
    }

    public StatsBuilder setWDef(float wDefMul) {
        this.wDef = new Stat(0, 0, wDefMul);
        return this;
    }

    public StatsBuilder setMDef(Stat mDefStat) {
        this.mDef = mDefStat;
        return this;
    }

    public StatsBuilder setMDef(int mDefStat, StatType type) {
        this.mDef = type == StatType.BASE ? new Stat(mDefStat, 0, 0) : new Stat(0, mDefStat, 0);
        return this;
    }

    public StatsBuilder setMDef(float mDefMul) {
        this.mDef = new Stat(0, 0, mDefMul);
        return this;
    }

    public StatsBuilder setAvd(Stat avdStat) {
        this.avd = avdStat;
        return this;
    }

    public StatsBuilder setAvd(int avdStat, StatType type) {
        this.avd = type == StatType.BASE ? new Stat(avdStat, 0, 0) : new Stat(0, avdStat, 0);
        return this;
    }

    public StatsBuilder setAvd(float avdMul) {
        this.avd = new Stat(0, 0, avdMul);
        return this;
    }

    public StatsBuilder setAcc(Stat accStat) {
        this.acc = accStat;
        return this;
    }

    public StatsBuilder setAcc(int accStat, StatType type) {
        this.acc = type == StatType.BASE ? new Stat(accStat, 0, 0) : new Stat(0, accStat, 0);
        return this;
    }

    public StatsBuilder setAcc(float accMul) {
        this.acc = new Stat(0, 0, accMul);
        return this;
    }

    public StatsBuilder setSpd(Stat spdStat) {
        this.spd = spdStat;
        return this;
    }

    public StatsBuilder setSpd(int spdStat, StatType type) {
        this.spd = type == StatType.BASE ? new Stat(spdStat, 0, 0) : new Stat(0, spdStat, 0);
        return this;
    }

    public StatsBuilder setSpd(float spdMul) {
        this.spd = new Stat(0, 0, spdMul);
        return this;
    }

    public StatsBuilder setCritRate(Stat critRateStat) {
        this.critRate = critRateStat;
        return this;
    }

    public StatsBuilder setCritRate(int critRateStat, StatType type) {
        this.critRate = type == StatType.BASE ? new Stat(critRateStat, 0, 0) : new Stat(0, critRateStat, 0);
        return this;
    }

    public StatsBuilder setCritRate(float critRateMul) {
        this.critRate = new Stat(0, 0, critRateMul);
        return this;
    }

    public StatsBuilder setCritDmg(Stat critDmgStat) {
        this.critDmg = critDmgStat;
        return this;
    }

    public StatsBuilder setCritDmg(int critDmgStat, StatType type) {
        this.critDmg = type == StatType.BASE ? new Stat(critDmgStat, 0, 0) : new Stat(0, critDmgStat, 0);
        return this;
    }

    public StatsBuilder setCritDmg(float critDmgMul) {
        this.critDmg = new Stat(0, 0, critDmgMul);
        return this;
    }

    public StatsBuilder setWAtt(Stat wAttStat) {
        this.wAtt = wAttStat;
        return this;
    }

    public StatsBuilder setWAtt(int wAttStat, StatType type) {
        this.wAtt = type == StatType.BASE ? new Stat(wAttStat, 0, 0) : new Stat(0, wAttStat, 0);
        return this;
    }

    public StatsBuilder setWAtt(float wAttMul) {
        this.wAtt = new Stat(0, 0, wAttMul);
        return this;
    }

    public StatsBuilder setMAtt(Stat mAttStat) {
        this.mAtt = mAttStat;
        return this;
    }

    public StatsBuilder setMAtt(int mAttStat, StatType type) {
        this.mAtt = type == StatType.BASE ? new Stat(mAttStat, 0, 0) : new Stat(0, mAttStat, 0);
        return this;
    }

    public StatsBuilder setMAtt(float mAttMul) {
        this.mAtt = new Stat(0, 0, mAttMul);
        return this;
    }

    public StatsBuilder setWMast(Stat wMastStat) {
        this.wMast = wMastStat;
        return this;
    }

    public StatsBuilder setWMast(int wMastStat, StatType type) {
        this.wMast = type == StatType.BASE ? new Stat(wMastStat, 0, 0) : new Stat(0, wMastStat, 0);
        return this;
    }

    public StatsBuilder setWMast(float wMastMul) {
        this.wMast = new Stat(0, 0, wMastMul);
        return this;
    }

    public Stats build() {
        return new Stats(str, wis, maxHP, maxMP, wDef, mDef, avd, acc, spd, critRate, critDmg, wAtt, mAtt, wMast
        );
    }
}
