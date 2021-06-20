package de.svenkubiak.jpushover.apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Builder;
import java.time.Duration;
import java.util.Objects;

import de.svenkubiak.jpushover.exceptions.JPushoverException;
import de.svenkubiak.jpushover.http.PushoverResponse;
import de.svenkubiak.jpushover.interfaces.MessageListener;
import de.svenkubiak.jpushover.listener.WebSocketListener;

/**
 * 
 * @author svenkubiak
 *
 */
public class OpenClient {
    private static final String LOGIN_URL = "https://api.pushover.net/1/users/login.json";
    private static final String DEVICE_URL = "https://api.pushover.net/1/devices.json";
    private static final String MESSAGES_URL = "https://api.pushover.net/1/messages.json";
    private static final String DELETE_URL = "https://api.pushover.net/1/devices/###DEVICE_ID###/update_highest_message.json";
    private static final String WEBSOCKET_URL = "wss://client.pushover.net/push";

    /**
     * Performs a Pushover login; required once for working with the Open Client API
     * 
     * @param email Your Pushover email address
     * @param password Your Pushover password
     * @param twofa Your Your current Pushover two-factor code (if enabled)
     * 
     * @return A PushoverResponse
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public PushoverResponse login(String email, String password, String twofa) throws JPushoverException {
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
        
        if (twofa != null) {
            params
                .append("&")
                .append("twofa")
                .append("=")
                .append(twofa);
        }
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOGIN_URL))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
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
            throw new JPushoverException("Login failed", e);
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
     * @param deviceId The deviceId to get the messages
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
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MESSAGES_URL + params.toString()))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
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
     * @param deviceId The deviceId to get the messages
     * @param messageId The messagesId
     * 
     * @return A PushoverResponse
     * @throws JPushoverException if something went wrong with the HTTP request
     */
    public PushoverResponse deleteMessages(String secret, String deviceId, String messageId) throws JPushoverException {
        Objects.requireNonNull(deviceId, "secret can not be null");
        Objects.requireNonNull(secret, "deviceId can not be null");
        Objects.requireNonNull(messageId, "deviceId can not be null");
        
        StringBuilder params = new StringBuilder()
                .append("secret")
                .append("=")
                .append(secret)
                .append("&")
                .append("message")
                .append("=")
                .append(messageId); 
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DELETE_URL.replace("###DEVICE_ID###", deviceId)))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
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
     * Establishes a WebSocket connect which listens to new messages
     * 
     * @param secret Your Pushover secret retrieved after login
     * @param deviceId The deviceId to get the messages
     * @param messageListener Your instance of a MessagesListener
     * 
     * @return True if the connection was established successful
     */
    public boolean listen(String secret, String deviceId, MessageListener messageListener) {
        Objects.requireNonNull(secret, "secret can not be null");
        Objects.requireNonNull(deviceId, "deviceId name can not be null");
        Objects.requireNonNull(messageListener, "messageListener can not be null");
        
        HttpClient httpClient = HttpClient.newBuilder().build();
        Builder webSocketBuilder = httpClient.newWebSocketBuilder();
        WebSocket webSocket = webSocketBuilder.buildAsync(URI.create(WEBSOCKET_URL), new WebSocketListener(messageListener)).join();
        
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
     * Registers a new device
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
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DEVICE_URL))
                .POST(HttpRequest.BodyPublishers.ofString(params.toString()))
                .build();

        PushoverResponse pushoverResponse = PushoverResponse.create().isSuccessful(false);
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                pushoverResponse
                .httpStatus(response.statusCode())
                .response(response.body())
                .isSuccessful(true);
            }
        } catch (IOException | InterruptedException e) {
            throw new JPushoverException("Failed to register new device", e);
        }
        
        return pushoverResponse;
    }
}