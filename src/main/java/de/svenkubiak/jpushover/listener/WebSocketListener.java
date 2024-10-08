package de.svenkubiak.jpushover.listener;

import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

public class WebSocketListener implements Listener {
    private final MessageListener messageListener;
    
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
                case "E", "R", "A":
                    messageListener.onError();
                break;
                default:
            }   
        }
        
        return Listener.super.onBinary(webSocket, data, last);
    }
}