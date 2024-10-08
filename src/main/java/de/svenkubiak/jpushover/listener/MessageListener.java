package de.svenkubiak.jpushover.listener;

public interface MessageListener {
    /**
     * Called when a new message is available/new messages are available
     */
    void onMessage();
    
    /**
     * Called when the WebSocket ran into an error
     */
    void onError();
}