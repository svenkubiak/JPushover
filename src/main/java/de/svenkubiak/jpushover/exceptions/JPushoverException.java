package de.svenkubiak.jpushover.exceptions;

/**
 * 
 * @author svenkubiak
 *
 */
public class JPushoverException extends Exception{
    private static final long serialVersionUID = 7468682477047138171L;

    public JPushoverException(String message, Exception e) {
        super(message, e);
    }
}