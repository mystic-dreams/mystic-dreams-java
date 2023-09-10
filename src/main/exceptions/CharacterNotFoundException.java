package main.exceptions;

public class CharacterNotFoundException extends Exception {
    public CharacterNotFoundException(String characterName) {
        super(characterName + " files could not be found.");
    }
}
