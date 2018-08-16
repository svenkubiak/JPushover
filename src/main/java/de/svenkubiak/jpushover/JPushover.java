package de.svenkubiak.jpushover;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.svenkubiak.jpushover.enums.Constants;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;

/**
 *
 * @author svenkubiak
 *
 */
public class JPushover {
    private static final Logger LOG = LogManager.getLogger(JPushover.class);
    private static final int HTTP_OK = 200;
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
    private Priority pushoverPriority;
    private Sound pushoverSound;
    private HttpHost proxy;
    private boolean pushoverHtml;
    
    public JPushover() {
        this.withSound(Sound.PUSHOVER);
        this.withPriority(Priority.NORMAL);
    }

    /**
     * Creates a new JPushover instance
     * @return JPushover instance
     */
    public static JPushover build() {
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
     * @param proxy The host that should be used as Proxy
     * @return JPushover instance
     */
    public final JPushover withProxy(final HttpHost proxy) {
        this.proxy = proxy;
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
     */
    public boolean validate() {
        Objects.requireNonNull(this.pushoverToken, "Token is required for validation");
        Objects.requireNonNull(this.pushoverUser, "User is required for validation");

        final List<NameValuePair> params = Form.form()
                .add(Constants.TOKEN.toString(), this.pushoverToken)
                .add(Constants.USER.toString(), this.pushoverUser)
                .add(Constants.DEVICE.toString(), this.pushoverDevice)
                .build();

        boolean valid = false;
        try {
        	final Request request = Request.Post(Constants.VALIDATION_URL.toString());
        	if (proxy != null) {
                request.viaProxy(proxy);        	    
        	}
        	
            final HttpResponse httpResponse = request
            		.bodyForm(params, Consts.UTF_8)
            		.execute()
            		.returnResponse();

            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HTTP_OK) {
                final String response = IOUtils.toString(httpResponse.getEntity().getContent(), Consts.UTF_8);
                if (StringUtils.isNotBlank(response) && response.contains("\"status\":1")) {
                    valid = true;
                }
            }
        } catch (final IOException e) {
            LOG.error("Failed to send validation requeste to pushover", e);
        }

        return valid;
    }

    /**
     * Sends a message to pushover
     *
     * @return JPushoverResponse instance
     */
    public final JPushoverResponse push() {
        Objects.requireNonNull(this.pushoverToken, "Token is required for a message");
        Objects.requireNonNull(this.pushoverUser, "User is required for a message");
        Objects.requireNonNull(this.pushoverMessage, "Message is required for a message");

        if (Priority.EMERGENCY.equals(this.pushoverPriority)) {
            Objects.requireNonNull(this.pushoverRetry, "Retry is required on priority emergency");
            Objects.requireNonNull(this.pushoverExpire, "Expire is required on priority emergency");
        }

        final List<NameValuePair> params = Form.form()
                .add(Constants.TOKEN.toString(), this.pushoverToken)
                .add(Constants.USER.toString(), this.pushoverUser)
                .add(Constants.MESSAGE.toString(), this.pushoverMessage)
                .add(Constants.DEVICE.toString(), this.pushoverDevice)
                .add(Constants.TITLE.toString(), this.pushoverTitle)
                .add(Constants.URL.toString(), this.pushoverUrl)
                .add(Constants.RETRY.toString(), this.pushoverRetry)
                .add(Constants.EXPIRE.toString(), this.pushoverExpire)
                .add(Constants.CALLBACK.toString(), this.pushoverCallback)
                .add(Constants.URLTITLE.toString(), this.pushoverUrlTitle)
                .add(Constants.PRIORITY.toString(), this.pushoverPriority.toString())
                .add(Constants.TIMESTAMP.toString(), this.pushoverTimestamp)
                .add(Constants.SOUND.toString(), this.pushoverSound.toString())
                .add(Constants.HTML.toString(), this.pushoverHtml ? "1" : "0")
                .build();

        final JPushoverResponse jPushoverResponse = new JPushoverResponse().isSuccessful(false);
        try {
            final HttpResponse httpResponse = Request.Post(Constants.MESSAGES_URL.toString())
            		.bodyForm(params, Consts.UTF_8)
            		.execute()
            		.returnResponse();

            if (httpResponse != null) {
                final int status = httpResponse.getStatusLine().getStatusCode();

                jPushoverResponse
                    .httpStatus(status)
                    .response(IOUtils.toString(httpResponse.getEntity().getContent(), Consts.UTF_8))
                    .isSuccessful((status == HTTP_OK) ? true : false);
            }
        } catch (final IOException e) {
            LOG.error("Failed to send message to pushover", e);
        }

        return jPushoverResponse;
    }

    public String getToken() {
        return pushoverToken;
    }

    public String getUser() {
        return pushoverUser;
    }

    public String getMessage() {
        return pushoverMessage;
    }

    public String getDevice() {
        return pushoverDevice;
    }

    public String getTitle() {
        return pushoverTitle;
    }

    public String getUrl() {
        return pushoverUrl;
    }

    public String getUrlTitle() {
        return pushoverUrlTitle;
    }

    public String getTimestamp() {
        return pushoverTimestamp;
    }

    public String getRetry() {
        return pushoverRetry;
    }

    public String getExpire() {
        return pushoverExpire;
    }

    public String getCallback() {
        return pushoverCallback;
    }

    public Priority getPriority() {
        return pushoverPriority;
    }

    public Sound getSound() {
        return pushoverSound;
    }

    public boolean isHtml() {
        return pushoverHtml;
    }
    
    public HttpHost getProxy() {
    	return proxy;
    }
}