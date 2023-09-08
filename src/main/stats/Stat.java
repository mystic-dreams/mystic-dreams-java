package main.stats;

public class Stat {
    private final int baseValue;
    private final int modifier;
    private final float multiplier;

    public Stat(int baseValue, int modifier, float multiplier) {
        this.baseValue = baseValue;
        this.modifier = modifier;
        this.multiplier = multiplier;
    }

    public Stat(int baseValue) {
        this(baseValue, 0, 1);
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getValue() {
        return Math.round((baseValue + modifier) * multiplier);
    }

    public Stat add(Stat other) {
        return new Stat(this.baseValue, this.modifier + other.baseValue + other.modifier,
                this.multiplier + other.multiplier);
    }

    public Stat remove(Stat other) {
        return new Stat(this.baseValue - other.baseValue, this.modifier - other.modifier,
                this.multiplier - other.multiplier);
    }

    public Stat clone() {
        return new Stat(this.baseValue, this.modifier, this.multiplier);
    }

}
