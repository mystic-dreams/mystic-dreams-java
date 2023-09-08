package main.exceptions;

public class CharacterNotFoundException extends Exception {
    private String characterName;

    public CharacterNotFoundException(String characterName) {
        super(characterName + " is not found.");
    }
}
