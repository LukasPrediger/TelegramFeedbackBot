package org.chase.telegram;

public class MissingValueException extends Exception {
    public MissingValueException(String key) {
        super("Couldn't get value for environment variable " + key);
    }
}
