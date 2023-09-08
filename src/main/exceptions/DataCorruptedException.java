package main.exceptions;

public class DataCorruptedException extends Exception {
    private String filename;

    public DataCorruptedException(String filename) {
        super("File " + filename + " has been corrupted");
    }
}
