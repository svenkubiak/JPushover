package de.svenkubiak.jpushover.exceptions;

import java.io.Serial;

public class JPushoverException extends Exception{
    @Serial
    private static final long serialVersionUID = 7468682477047138171L;

    public JPushoverException(String message, Exception e) {
        super(message, e);
    }
}