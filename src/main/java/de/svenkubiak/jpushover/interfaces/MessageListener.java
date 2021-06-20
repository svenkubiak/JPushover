package de.svenkubiak.jpushover.interfaces;

/**
 * 
 * @author svenkubiak
 *
 */
public interface MessageListener {
    /**
     * Called when a new message/new messages is available
     */
    void onMessage();
    
    /**
     * Called when the WebSocket ran into an error
     */
    void onError();
}