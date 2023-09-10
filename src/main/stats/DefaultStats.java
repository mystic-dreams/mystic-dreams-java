package main.stats;

public class DefaultStats {
    public static final int BASE_WMAST = 10;
    public static final int BASE_CRIT_DMG = 120;
    public static final Stats WARRIOR_STARTER_STATS = new StatsBuilder()
            .setStr(new Stat(20, 0, 1))
            .setWis(new Stat(4, 0, 1))
            .setMaxHP(new Stat(100, 0, 1))
            .setMaxMP(new Stat(50, 0, 1))
            .setWDef(new Stat(20, 0, 1))
            .setMDef(new Stat(10, 0, 1))
            .setAvd(new Stat(20, 0, 1))
            .setAcc(new Stat(20, 0, 1))
            .setSpd(new Stat(20, 0, 1))
            .setCritRate(new Stat(5, 0, 1))
            .setCritDmg(new Stat(BASE_CRIT_DMG, 0, 1))
            .setWAtt(new Stat(0, 0, 1))
            .setMAtt(new Stat(0, 0, 1))
            .setWMast(new Stat(BASE_WMAST, 0, 1))
            .build();

    public static final Stats MAGICIAN_STARTER_STATS = new StatsBuilder()
            .setStr(new Stat(4, 0, 1))
            .setWis(new Stat(20, 0, 1))
            .setMaxHP(new Stat(100, 0, 1))
            .setMaxMP(new Stat(50, 0, 1))
            .setWDef(new Stat(10, 0, 1))
            .setMDef(new Stat(20, 0, 1))
            .setAvd(new Stat(20, 0, 1))
            .setAcc(new Stat(20, 0, 1))
            .setSpd(new Stat(20, 0, 1))
            .setCritRate(new Stat(5, 0, 1))
            .setCritDmg(new Stat(BASE_CRIT_DMG, 0, 1))
            .setWAtt(new Stat(0, 0, 1))
            .setMAtt(new Stat(0, 0, 1))
            .setWMast(new Stat(BASE_WMAST, 0, 1))
            .build();
}
