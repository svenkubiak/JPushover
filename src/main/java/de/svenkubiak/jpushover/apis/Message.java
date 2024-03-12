package de.svenkubiak.jpushover.apis;

import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import de.svenkubiak.jpushover.enums.Param;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;
import de.svenkubiak.jpushover.enums.Url;
import de.svenkubiak.jpushover.exceptions.JPushoverException;
import de.svenkubiak.jpushover.http.PushoverRequest;
import de.svenkubiak.jpushover.http.PushoverResponse;
import de.svenkubiak.jpushover.services.AsyncExecutor;
import de.svenkubiak.jpushover.services.AsyncService;
import de.svenkubiak.jpushover.utils.Validate;

/**
 * 
 * @author svenkubiak
 *
 */
public class Message implements API {
    private final NavigableMap<String, String> body = new TreeMap<>();
    private String proxyHost;
    private int proxyPort;
    
    public Message() {
        this.withPriority(Priority.NORMAL);
        this.withSound(Sound.PUSHOVER);
    }
    
    /**
     * Your application's API token
     * (required)
     *
     * @param token The pushover API token
     * @return Message instance
     */
    public Message withToken(String token) {
        body.put(Param.TOKEN.toString(), token);
        return this;
    }

    /**
     * The user/group key (not e-mail address) of your user (or you),
     * viewable when logged into the @see <a href="https://pushover.net/login">pushover dashboard</a>
     * (required)
     *
     * @param user The username
     * @return Message instance
     */
    public Message withUser(String user) {
        body.put(Param.USER.toString(), user);
        return this;
    }

    /**
     * Specifies how often (in seconds) the Pushover servers will send the same notification to the user.
     * Only required if priority is set to emergency.
     * (optional)
     *
     * @param retry Number of seconds
     * @return Message instance
     */
    public Message withRetry(int retry) {
        body.put(Param.RETRY.toString(), String.valueOf(retry));
        return this;
    }

    /**
     * Specifies how many seconds your notification will continue to be retried for (every retry seconds).
     * Only required if priority is set to emergency.
     * (optional)
     *
     * @param expire Number of seconds
     * @return Message instance
     */
    public Message withExpire(int expire) {
        body.put(Param.EXPIRE.toString(), String.valueOf(expire));
        return this;
    }

    /**
     * Your message
     * (required)
     *
     * @param message The message to sent
     * @return Message instance
     */
    public Message withMessage(String message) {
        body.put(Param.MESSAGE.toString(), message);
        return this;
    }

    /**
     * Your user's device name to send the message directly to that device,
     * rather than all of the user's devices
     * (optional)
     *
     * @param device The device name
     * @return Message instance
     */
    public Message withDevice(String device) {
        body.put(Param.DEVICE.toString(), device);
        return this;
    }

    /**
     * Your message's title, otherwise your app's name is used
     * (optional)
     *
     * @param title The title
     * @return Message instance
     */
    public Message withTitle(String title) {
        body.put(Param.TITLE.toString(), title);
        return this;
    }

    /**
     * A supplementary URL to show with your message
     * (optional)
     *
     * @param url The url
     * @return Message instance
     */
    public Message withUrl(String url) {
        body.put(Param.URL.toString(), url);
        body.put(Param.URL_TITLE.toString(), url);
        return this;
    }
    
    /**
     * Enables HTML in the pushover message
     * Either HTML or monospace can be enabled
     * (optional)
     *
     * @return Message instance
     */
    public Message enableMonospace() {
        body.put(Param.MONOSPACE.toString(), "1");
        body.put(Param.HTML.toString(), "0");
        return this;
    }
    
    /**
     * Enables HTML in the pushover message
     * Either HTML or monospace can be enabled
     * (optional)
     *
     * @return Message instance
     */
    public Message enableHtml() {
        body.put(Param.MONOSPACE.toString(), "0");
        body.put(Param.HTML.toString(), "1");
        return this;
    }

    /**
     * A title for your supplementary URL, otherwise just the URL is shown
     * (optional)
     *
     * @param urlTitle The url title
     * @return Message instance
     */
    public Message withUrlTitle(String urlTitle) {
        body.put(Param.URL_TITLE.toString(), urlTitle);
        return this;
    }

    /**
     * A Unix timestamp of your message's date and time to display to the user,
     * rather than the time your message is received by our API
     * (optional)
     *
     * @param timestamp The Unix timestamp
     * @return Message instance
     */
    public Message withTimestamp(int timestamp) {
        body.put(Param.TIMESTAMP.toString(), String.valueOf(timestamp));
        return this;
    }

    /**
     * Priority of the message based on the @see <a href="https://pushover.net/api#priority">documentation</a>
     * (optional)
     *
     * @param priority The priority enum
     * @return Message instance
     */
    public Message withPriority(Priority priority) {
        body.put(Param.PRIORITY.toString(), priority.toString());
        return this;
    }

    /**
     * The name of one of the sounds supported by device clients to override
     * the user's default sound choice
     * (optional)
     *
     * @param sound THe sound enum
     * @return Message instance
     */
    public Message withSound(Sound sound) {
        body.put(Param.SOUND.toString(), sound.toString());
        return this;
    }

    /**
     * Callback parameter may be supplied with a publicly-accessible URL that the
     * pushover servers will send a request to when the user has acknowledged your
     * notification.
     * Only used if priority is set to emergency.
     * (optional)
     *
     * @param callback The callback URL
     * @return Message instance
     */
    public Message withCallback(String callback) {
        body.put(Param.CALLBACK.toString(), callback);
        return this;
    }

    /**
     * Adds a ttl to the Pushover message
     * 
     * @param ttl Seconds until this message should be automatically removed from the device. Needs to be positive
     * @return Message instance
     */
    public Message withTTL(int ttl) {
        Validate.checkArgument(ttl > 0, "TTL must be a positive value");
        
        body.put(Param.TTL.toString(), String.valueOf(ttl));
        return this;
    }

    /**
     * Sets the host and port for a proxy connection if needed
     *
     * @param proxyHost the host for the proxy
     * @param proxyPort the port for the proxy
     *
     * @return GLance instance
     */
    public Message withProxy(String proxyHost, int proxyPort) {
        Objects.requireNonNull(proxyHost, "proxyHost can not be null");
        Validate.checkArgument(proxyPort > 0, "proxyPort must be greater than null");

        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;

        return this;
    }

    /**
     * Sends a validation request to pushover ensuring that the token and user
     * is correct, that there is at least one active device on the account.
     *
     * Requires token parameter
     * Requires user parameter
     * Optional device parameter to check specific device
     *
     * @return true if token and user are valid and at least on device is on the account, false otherwise
     *
     * @throws JPushoverException on failure
     */
    public boolean validate() throws JPushoverException {
        Objects.requireNonNull(body.get(Param.TOKEN.toString()), "Token is required for validation");
        Objects.requireNonNull(body.get(Param.USER.toString()), "User is required for validation");

        var pushoverResponse = new PushoverRequest().push(Url.VALIDATE.toString(), body, this.proxyHost, this.proxyPort);
        
        var valid = false;
        if (pushoverResponse.getHttpStatus() == 200) {
            var response = pushoverResponse.getResponse();
            if (response != null && response.contains("\"status\":1")) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Sends a message to pushover
     *
     * @return PushoverResponse instance
     *
     * @throws JPushoverException on failure
     */
    @Override
    public PushoverResponse push() throws JPushoverException {
        Objects.requireNonNull(body.get(Param.TOKEN.toString()), "Token is required for a message");
        Objects.requireNonNull(body.get(Param.USER.toString()), "User is required for a message");
        Objects.requireNonNull(body.get(Param.MESSAGE.toString()), "Message is required for a message");
        Validate.checkArgument(body.get(Param.MESSAGE.toString()).length() <= 1024, "Message can not exceed more than 1024 characters");
        
        if (Priority.EMERGENCY.toString().equals(body.get(Param.PRIORITY.toString()))) {
            body.putIfAbsent(Param.RETRY.toString(), "60");
            body.putIfAbsent(Param.EXPIRE.toString(), "3600");
        }
        
        if (body.get(Param.TITLE.toString()) != null) {
            Validate.checkArgument(body.get(Param.TITLE.toString()).length() <= 250, "Title can not exceed more than 250 characters");
        }
        
        if (body.get(Param.URL.toString()) != null) {
            Validate.checkArgument(body.get(Param.URL.toString()).length() <= 512, "URL can not exceed more than 512 characters");
        }
        
        if (body.get(Param.URL_TITLE.toString()) != null) {
            Validate.checkArgument(body.get(Param.URL_TITLE.toString()).length() <= 100, "URL Title can not exceed more than 100 characters");
        }
        
        return new PushoverRequest().push(Url.MESSAGES.toString(), body, this.proxyHost, this.proxyPort);
    }
    
    /**
     * Sends a message to pushover asynchronously
     *
     * @return PushoverResponse instance
     *
     * @throws InterruptedException if sending the message fails
     * @throws ExecutionException if sending the message fails
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Future<PushoverResponse> pushAsync() throws InterruptedException, ExecutionException {
        return AsyncService.getInstance().execute(new AsyncExecutor(this));
    }
    
    public String getValue(String param) {
        Objects.requireNonNull(param, "param can not be null");
        
        return body.get(param);
    }
}