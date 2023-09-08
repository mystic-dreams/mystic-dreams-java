package main.agents;

import main.exceptions.InvalidJobException;

public enum JobClass {
    WARRIOR("Warrior"),
    MAGICIAN("Magician");

    public final String value;

    JobClass(String value) {
        this.value = value;
    }

    public static JobClass parse(String value) throws InvalidJobException {
        switch (value.toLowerCase()) {
            case "warrior" -> {
                return JobClass.WARRIOR;
            }
            case "magician" -> {
                return JobClass.MAGICIAN;
            }
            default -> {
                throw new InvalidJobException(value);
            }
        }
    }

}
