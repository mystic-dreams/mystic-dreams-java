package main.stats;

public class DefaultStats {
    public static final int BASE_WMAST = 10;
    public static final int BASE_CRIT_DMG = 120;
    public static final Stats WARRIOR_STARTER_STATS = new StatsBuilder()
            .setStr(20, StatType.BASE)
            .setWis(4, StatType.BASE)
            .setMaxHP(100, StatType.BASE)
            .setMaxMP(50, StatType.BASE)
            .setWDef(20, StatType.BASE)
            .setMDef(10, StatType.BASE)
            .setAvd(20, StatType.BASE)
            .setAcc(20, StatType.BASE)
            .setSpd(20, StatType.BASE)
            .setCritRate(5, StatType.BASE)
            .setCritDmg(BASE_CRIT_DMG, StatType.BASE)
            .setWAtt(0, StatType.BASE)
            .setMAtt(0, StatType.BASE)
            .setWMast(BASE_WMAST, StatType.BASE)
            .build();

    public static final Stats MAGICIAN_STARTER_STATS = new StatsBuilder()
            .setStr(4, StatType.BASE)
            .setWis(20, StatType.BASE)
            .setMaxHP(100, StatType.BASE)
            .setMaxMP(50, StatType.BASE)
            .setWDef(10, StatType.BASE)
            .setMDef(20, StatType.BASE)
            .setAvd(20, StatType.BASE)
            .setAcc(20, StatType.BASE)
            .setSpd(20, StatType.BASE)
            .setCritRate(5, StatType.BASE)
            .setCritDmg(BASE_CRIT_DMG, StatType.BASE)
            .setWAtt(0, StatType.BASE)
            .setMAtt(0, StatType.BASE)
            .setWMast(BASE_WMAST, StatType.BASE)
            .build();
}
