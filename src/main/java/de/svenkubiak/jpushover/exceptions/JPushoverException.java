package de.svenkubiak.jpushover.exceptions;

/**
 * 
 * @author svenkubiak
 *
 */
public class JPushoverException extends Exception{
    private static final long serialVersionUID = -5719174030861964503L;
    
    public JPushoverException(String message, Exception e) {
        super(message, e);
    }
}