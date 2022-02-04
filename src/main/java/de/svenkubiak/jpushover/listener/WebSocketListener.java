package de.svenkubiak.jpushover.listener;

import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

/**
 * 
 * @author svenkubiak
 *
 */
public class WebSocketListener implements Listener {
    private MessageListener messageListener;
    
    public WebSocketListener (MessageListener messageListener) {
        Objects.requireNonNull(messageListener, "messageListener can not be null");
        this.messageListener = messageListener;
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        messageListener.onError();
        Listener.super.onError(webSocket, error);
    }
    
    @Override
    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        if (data != null) {
            var frame = StandardCharsets.UTF_8.decode(data).toString();
            switch (frame) {
                case "!":
                    messageListener.onMessage();
                break;
                case "E":
                    messageListener.onError();
                break; 
                case "R":
                    messageListener.onError();
                break;     
                case "A":
                    messageListener.onError();
                break;          
                default:
            }   
        }
        
        return Listener.super.onBinary(webSocket, data, last);
    }
}