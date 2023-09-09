package main.exceptions;

public class InvalidSkillException extends Exception {
    private String skillName;

    public InvalidSkillException(String skillName) {
        super();
        this.skillName = skillName;
    }
}
