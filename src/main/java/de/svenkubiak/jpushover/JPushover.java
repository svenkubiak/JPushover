package de.svenkubiak.jpushover;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

import de.svenkubiak.jpushover.enums.Constants;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;

/**
 *
 * Zero-dependency convenient class for sending messages to Pushover
 *
 * @author svenkubiak
 *
 */
public class JPushover {
    private static final int HTTP_OK = 200;
    private Priority pushoverPriority;
    private Sound pushoverSound;
    private String pushoverToken;
    private String pushoverUser;
    private String pushoverMessage;
    private String pushoverDevice;
    private String pushoverTitle;
    private String pushoverUrl;
    private String pushoverUrlTitle;
    private String pushoverTimestamp;
    private String pushoverRetry;
    private String pushoverExpire;
    private String pushoverCallback;
    private String proxyHost;
    private int proxyPort;
    private boolean pushoverHtml;
    
    public JPushover() {
        this.withSound(Sound.PUSHOVER);
        this.withPriority(Priority.NORMAL);
    }

    /**
     * Creates a new JPushover instance
     * @return JPushover instance
     */
    public static JPushover create() {
        return new JPushover();
    }

    /**
     * Your application's API token
     * (required)
     *
     * @param token The pushover API token
     * @return JPushover instance
     */
    public final JPushover withToken(final String token) {
        this.pushoverToken = token;
        return this;
    }

    /**
     * The user/group key (not e-mail address) of your user (or you),
     * viewable when logged into the @see <a href="https://pushover.net/login">pushover dashboard</a>
     * (required)
     *
     * @param user The username
     * @return JPushover instance
     */
    public final JPushover withUser(final String user) {
        this.pushoverUser = user;
        return this;
    }

    /**
     * Specifies how often (in seconds) the Pushover servers will send the same notification to the user.
     * Only required if priority is set to emergency.
     *
     * @param retry Number of seconds
     * @return JPushover instance
     */
    public final JPushover withRetry(final String retry) {
        this.pushoverRetry = retry;
        return this;
    }

    /**
     * Specifies how many seconds your notification will continue to be retried for (every retry seconds).
     * Only required if priority is set to emergency.
     *
     * @param expire Number of seconds
     * @return JPushover instance
     */
    public final JPushover withExpire(final String expire) {
        this.pushoverExpire = expire;
        return this;
    }

    /**
     * Your message
     * (required)
     *
     * @param message The message to sent
     * @return JPushover instance
     */
    public final JPushover withMessage(final String message) {
        this.pushoverMessage = message;
        return this;
    }

    /**
     * Your user's device name to send the message directly to that device,
     * rather than all of the user's devices
     * (optional)
     *
     * @param device The device name
     * @return JPushover instance
     */
    public final JPushover withDevice(final String device) {
        this.pushoverDevice = device;
        return this;
    }

    /**
     * Your message's title, otherwise your app's name is used
     * (optional)
     *
     * @param title The title
     * @return JPushover instance
     */
    public final JPushover withTitle(final String title) {
        this.pushoverTitle = title;
        return this;
    }

    /**
     * A supplementary URL to show with your message
     * (optional)
     *
     * @param url The url
     * @return JPushover instance
     */
    public final JPushover withUrl(final String url) {
        this.pushoverUrl = url;
        return this;
    }

    /**
     * Enables HTML in the pushover message
     * (optional)
     *
     * @return JPushover instance
     */
    public final JPushover enableHtml() {
        this.pushoverHtml = true;
        return this;
    }

    /**
     * A title for your supplementary URL, otherwise just the URL is shown
     *
     * @param urlTitle The url title
     * @return JPushover instance
     */
    public final JPushover withUrlTitle(final String urlTitle) {
        this.pushoverUrlTitle = urlTitle;
        return this;
    }

    /**
     * A Unix timestamp of your message's date and time to display to the user,
     * rather than the time your message is received by our API
     *
     * @param timestamp The Unix timestamp
     * @return JPushover instance
     */
    public final JPushover withTimestamp(final String timestamp) {
        this.pushoverTimestamp = timestamp;
        return this;
    }

    /**
     * Priority of the message based on the @see <a href="https://pushover.net/api#priority">documentation</a>
     * (optional)
     *
     * @param priority The priority enum
     * @return JPushover instance
     */
    public final JPushover withPriority(final Priority priority) {
        this.pushoverPriority = priority;
        return this;
    }

    /**
     * The name of one of the sounds supported by device clients to override
     * the user's default sound choice
     * (optional)
     *
     * @param sound THe sound enum
     * @return JPushover instance
     */
    public final JPushover withSound(final Sound sound) {
        this.pushoverSound = sound;
        return this;
    }

    /**
     * Callback parameter may be supplied with a publicly-accessible URL that the
     * pushover servers will send a request to when the user has acknowledged your
     * notification.
     * Only required if priority is set to emergency.
     *
     * @param callback The callback URL
     * @return JPushover instance
     */
    public final JPushover withCallback(final String callback) {
        this.pushoverCallback = callback;
        return this;
    }

    /**
     * Uses the given proxy for HTTP requests
     *
     * @param proxyHost The host that should be used for the Proxy
     * @param proxyPort The port that should be used for the Proxy
     * @return JPushover instance
     */
    public final JPushover withProxy(final String proxyHost, final int proxyPort) {
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
     * @throws IOException if validation fails
     * @throws InterruptedException if validation fails
     */
    public boolean validate() throws IOException, InterruptedException {
        Objects.requireNonNull(this.pushoverToken, "Token is required for validation");
        Objects.requireNonNull(this.pushoverUser, "User is required for validation");

        NavigableMap<String, String> body = new TreeMap<>();
        body.put(Constants.TOKEN.toString(), this.pushoverToken);
        body.put(Constants.USER.toString(), this.pushoverUser);

        var httpResponse = getResponse(toJson(body), Constants.VALIDATION_URL.toString());

        var valid = false;
        if (httpResponse.statusCode() == HTTP_OK) {
            var response = httpResponse.body();
            if (response != null && response.contains("\"status\":1")) {
                valid = true;
            }
        }

        return valid;
    }

    /**
     * Sends a message to pushover
     *
     * @return JPushoverResponse instance
     *
     * @throws IOException if sending the message fails
     * @throws InterruptedException if sending the message fails
     */
    public final JPushoverResponse push() throws IOException, InterruptedException {
        Objects.requireNonNull(this.pushoverToken, "Token is required for a message");
        Objects.requireNonNull(this.pushoverUser, "User is required for a message");
        Objects.requireNonNull(this.pushoverMessage, "Message is required for a message");

        if (Priority.EMERGENCY.equals(this.pushoverPriority)) {
            Objects.requireNonNull(this.pushoverRetry, "Retry is required on priority emergency");
            Objects.requireNonNull(this.pushoverExpire, "Expire is required on priority emergency");
        }

        NavigableMap<String, String> body = new TreeMap<>();
        body.put(Constants.TOKEN.toString(), this.pushoverToken);
        body.put(Constants.USER.toString(), this.pushoverUser);
        body.put(Constants.MESSAGE.toString(), this.pushoverMessage);
        body.put(Constants.DEVICE.toString(), this.pushoverDevice);
        body.put(Constants.TITLE.toString(), this.pushoverTitle);
        body.put(Constants.URL.toString(), this.pushoverUrl);
        body.put(Constants.RETRY.toString(), this.pushoverRetry);
        body.put(Constants.EXPIRE.toString(), this.pushoverExpire);
        body.put(Constants.CALLBACK.toString(), this.pushoverCallback);
        body.put(Constants.URLTITLE.toString(), this.pushoverUrlTitle);
        body.put(Constants.PRIORITY.toString(), this.pushoverPriority.toString());
        body.put(Constants.TIMESTAMP.toString(), this.pushoverTimestamp);
        body.put(Constants.SOUND.toString(), this.pushoverSound.toString());
        body.put(Constants.HTML.toString(), this.pushoverHtml ? "1" : "0");

        var httpResponse = getResponse(toJson(body), Constants.MESSAGES_URL.toString());

        var jPushoverResponse = new JPushoverResponse().isSuccessful(false);
        jPushoverResponse
            .httpStatus(httpResponse.statusCode())
            .response(httpResponse.body())
            .isSuccessful((httpResponse.statusCode() == HTTP_OK) ? true : false);

        return jPushoverResponse;
    }

    private HttpResponse<String> getResponse(String body, String url) throws IOException, InterruptedException {
        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        var httpClientBuilder = HttpClient.newBuilder();

        if (this.proxyHost != null && this.proxyPort > 0) {
            httpClientBuilder.proxy(ProxySelector.of(new InetSocketAddress(this.proxyHost, this.proxyPort)));
        }

        return httpClientBuilder.build().send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private String toJson(NavigableMap<String, String> body) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("{");
        for (Map.Entry<String, String> entry : body.entrySet()) {
            buffer.append("\"").append(entry.getKey()).append("\"");
            buffer.append(":");
            buffer.append("\"").append(entry.getValue()).append("\"");
            buffer.append(",");
        }
        buffer.append("}");

        return buffer.toString().replace(",}", "}");
    }
}