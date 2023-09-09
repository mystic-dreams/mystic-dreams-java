package main.exceptions;

public class DataCorruptedException extends Exception {

    public DataCorruptedException(String characterName) {
        super(characterName + "'s file has been corrupted");
    }
}
