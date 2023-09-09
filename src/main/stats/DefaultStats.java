package main.stats;

public class DefaultStats {
    private static final int baseWMast = 10;
    private static final int baseCritDmg = 120;
    public static final Stats WARRIOR_STARTER_STATS = new StatsBuilder()
            .setStr(20)
            .setWis(4)
            .setMaxHP(100)
            .setMaxMP(50)
            .setWDef(20)
            .setMDef(10)
            .setAvd(20)
            .setAcc(20)
            .setSpd(20)
            .setCritRate(5)
            .setCritDmg(baseCritDmg)
            .setWAtt(0)
            .setMAtt(0)
            .setWMast(baseWMast)
            .build();

    public static final Stats MAGICIAN_STARTER_STATS = new StatsBuilder()
            .setStr(20)
            .setWis(4)
            .setMaxHP(100)
            .setMaxMP(50)
            .setWDef(10)
            .setMDef(20)
            .setAvd(20)
            .setAcc(20)
            .setSpd(20)
            .setCritRate(5)
            .setCritDmg(baseCritDmg)
            .setWAtt(0)
            .setMAtt(0)
            .setWMast(baseWMast)
            .build();
}
