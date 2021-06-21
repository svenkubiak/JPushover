package de.svenkubiak.jpushover.apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.Objects;

import de.svenkubiak.jpushover.enums.Url;
import de.svenkubiak.jpushover.exceptions.JPushoverException;
import de.svenkubiak.jpushover.http.PushoverResponse;
import de.svenkubiak.jpushover.listener.MessageListener;
import de.svenkubiak.jpushover.listener.WebSocketListener;

/**
 * 
 * @author svenkubiak
 *
 */
public class OpenClient {
    private HttpClient client = HttpClient.newHttpClient();
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private WebSocket webSocket;
    
    /**
     * Performs a Pushover login; required once for working with the Open Client API
     * 
     * @param email Your Pushover email address
     * @param password Your Pushover password
     * @param twoFactor Your current Pushover two-factor code (if enabled)
     * 
     * @return A PushoverResponse
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public PushoverResponse login(String email, String password, String twoFactor) throws JPushoverException {
        Objects.requireNonNull(email, "email can not be null");
        Objects.requireNonNull(password, "password can not be null");
        
        StringBuilder params = new StringBuilder()
                .append("email")
                .append("=")
                .append(email)
                .append("&")
                .append("password")
                .append("=")
                .append(password);
        
        if (twoFactor != null) {
            params
                .append("&")
                .append("twofa")
                .append("=")
                .append(twoFactor);
        }
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Url.LOGIN.toString()))
                .timeout(TIMEOUT)
                .POST(HttpRequest.BodyPublishers.ofString(params.toString()))
                .build();
        
        PushoverResponse pushoverResponse = PushoverResponse.create().isSuccessful(false);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            pushoverResponse
                .httpStatus(response.statusCode())
                .response(response.body())
                .isSuccessful((response.statusCode() == 200) ? true : false);
        } catch (IOException | InterruptedException e) {
            throw new JPushoverException("Pushover Login failed", e);
        }
        
        return pushoverResponse;
    }
    
    /**
     * Performs a Pushover login; required once for working with the Open Client API
     * 
     * @param email Your Pushover email address
     * @param password Your Pushover password
     * 
     * @return A PushoverResponse
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public PushoverResponse login(String email, String password) throws JPushoverException {
        return login(email, password, null);
    }
    
    /**
     * Retrieves all available messages for the given deviceId
     * 
     * @param secret Your Pushover secret retrieved after login
     * @param deviceId The deviceId from whom to get the messages
     * 
     * @return A String containing raw Json with all available messages or null
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public String messages(String secret, String deviceId) throws JPushoverException {
        Objects.requireNonNull(secret, "secret can not be null");
        Objects.requireNonNull(deviceId, "deviceId can not be null");
        
        StringBuilder params = new StringBuilder()
                .append("?secret")
                .append("=")
                .append(secret)
                .append("&")
                .append("deviceId")
                .append("=")
                .append(deviceId); 
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Url.MESSAGES.toString() + params.toString()))
                .timeout(TIMEOUT)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .build();
        
        String messages = null;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            messages = response.body();
        } catch (IOException | InterruptedException e) {
            throw new JPushoverException("Failed to get messages", e);
        }
        
        return messages;
    }
    
    /**
     * Deletes all messages after (and including) a given messagesId
     * 
     * @param secret Your Pushover secret retrieved after login
     * @param deviceId The deviceId whom to get the messages
     * @param messageId The messagesId
     * 
     * @return A PushoverResponse
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public PushoverResponse deleteMessages(String secret, String deviceId, String messageId) throws JPushoverException {
        Objects.requireNonNull(deviceId, "secret can not be null");
        Objects.requireNonNull(secret, "deviceId can not be null");
        Objects.requireNonNull(messageId, "messageId can not be null");
        
        StringBuilder params = new StringBuilder()
                .append("secret")
                .append("=")
                .append(secret)
                .append("&")
                .append("message")
                .append("=")
                .append(messageId); 
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Url.DELETE.toString().replace("###DEVICE_ID###", deviceId)))
                .timeout(TIMEOUT)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(params.toString()))
                .build();

        PushoverResponse pushoverResponse = PushoverResponse.create().isSuccessful(false);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            pushoverResponse
                .httpStatus(response.statusCode())
                .response(response.body())
                .isSuccessful((response.statusCode() == 200) ? true : false);
        } catch (IOException | InterruptedException e) {
            throw new JPushoverException("Failed to delete messages", e);
        }
        
        return pushoverResponse;
    }
    
    /**
     * Establishes a WebSocket connection which listens to incoming messages
     * 
     * @param secret Your Pushover secret retrieved after login
     * @param deviceId The deviceId from whom to get the messages
     * @param messageListener Your instance of a MessagesListener
     * 
     * @return True if the connection was established successfully
     */
    public boolean open(String secret, String deviceId, MessageListener messageListener) {
        Objects.requireNonNull(secret, "secret can not be null");
        Objects.requireNonNull(deviceId, "deviceId name can not be null");
        Objects.requireNonNull(messageListener, "messageListener can not be null");
        
        webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create(Url.WEBSOCKET.toString()), new WebSocketListener(messageListener))
                .join();
        
        StringBuilder params = new StringBuilder()
                .append("login")
                .append(":")
                .append(deviceId)
                .append(":")
                .append(secret)
                .append("\n"); 
        
        webSocket.sendText(params.toString(), true);
        
        return !webSocket.isInputClosed();
    }
    
    /**
     * Registers a new device at Pushover
     * 
     * @param secret Your Pushover secret retrieved after login
     * @param deviceName The name of the device to register
     * 
     * @return A PushoverResponse
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public PushoverResponse registerDevice(String secret, String deviceName) throws JPushoverException {
        Objects.requireNonNull(secret, "secret can not be null");
        Objects.requireNonNull(deviceName, "device name can not be null");
        
        StringBuilder params = new StringBuilder()
                .append("secret")
                .append("=")
                .append(secret)
                .append("&")
                .append("name")
                .append("=")
                .append(deviceName)
                .append("&os=O"); 
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Url.DEVICE.toString()))
                .POST(HttpRequest.BodyPublishers.ofString(params.toString()))
                .build();

        PushoverResponse pushoverResponse = PushoverResponse.create().isSuccessful(false);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            pushoverResponse
                .httpStatus(response.statusCode())
                .response(response.body())
                .isSuccessful(true);
        } catch (IOException | InterruptedException e) {
            throw new JPushoverException("Failed to register new device", e);
        }
        
        return pushoverResponse;
    }
    
    /**
     * Closes the existing WebSocket connection to the Pushover API
     * 
     * @return true if close was successful, false otherwise
     */
    public boolean close() {
        boolean closed = false;
        if (webSocket != null) {
            webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok");
            closed = webSocket.isInputClosed() && webSocket.isOutputClosed();
            webSocket = null;
        }
        
        return closed;
    }
}