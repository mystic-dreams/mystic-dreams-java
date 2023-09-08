package main.exceptions;

public class InvalidJobException extends Exception {

    public InvalidJobException(String jobName) {
        super("Invalid job " + jobName);
    }
}
