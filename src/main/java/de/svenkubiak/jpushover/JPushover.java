package de.svenkubiak.jpushover;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

import de.svenkubiak.jpushover.enums.Constants;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;

/**
 * 
 * @author svenkubiak
 *
 */
public class JPushover {
    private static final Logger LOG = LoggerFactory.getLogger(JPushover.class);

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

    public JPushover(){
        this.pushoverSound = Sound.PUSHOVER;
        this.pushoverPriority = Priority.NORMAL;
    }

    /**
     * Your application's API token
     * (required) 
     * 
     * @param token The pushover API token
     * @return JPushover instance
     */
    public JPushover token(String token) {
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
    public JPushover user(String user) {
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
    public JPushover retry(String retry) {
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
    public JPushover expire(String expire) {
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
    public JPushover message(String message) {
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
    public JPushover device(String device) {
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
    public JPushover title(String title) {
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
    public JPushover url(String url) {
        this.pushoverUrl = url;
        return this;
    }

    /**
     * A title for your supplementary URL, otherwise just the URL is shown
     * 
     * @param urlTitle The url title
     * @return JPushover instance
     */
    public JPushover urlTitle(String urlTitle) {
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
    public JPushover timestamp(String timestamp) {
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
    public JPushover priority(Priority priority) {
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
    public JPushover sound(Sound sound) {
        this.pushoverSound = sound;
        return this;
    }
    
    /**
     * Callback parameter may be supplied with a publicly-accessible URL that the
     * Pushover servers will send a request to when the user has acknowledged your
     * notification.
     * Only required if priority is set to emergency.
     * 
     * @param callback
     * @return
     */
    public JPushover callback(String callback) {
        this.pushoverCallback = callback;
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
        Preconditions.checkNotNull(this.pushoverToken, "Token is required for validation");
        Preconditions.checkNotNull(this.pushoverUser, "User is required for validation");
        
        List<NameValuePair> params = Form.form()
                .add(Constants.TOKEN.get(), this.pushoverToken)
                .add(Constants.USER.get(), this.pushoverUser)
                .add(Constants.DEVICE.get(), this.pushoverDevice)
                .build();
        
        HttpResponse httpResponse = null;
        boolean valid = false;
        try {
            httpResponse = Request.Post(Constants.VALIDATION_URL.get()).bodyForm(params, Consts.UTF_8).execute().returnResponse();
            
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                String response = IOUtils.toString(httpResponse.getEntity().getContent());
                if (StringUtils.isNotBlank(response) && response.contains("\"status\":1")) {
                    valid = true;
                }
            }
        } catch (IOException e) {
            LOG.error("Failed to send validation requeste to pushover", e);
        }
        
        return valid;
    }

    /**
     * Sends a message to pushover
     * 
     * @return JPushoverResponse instance
     */
    public JPushoverResponse push() {
        Preconditions.checkNotNull(this.pushoverToken, "Token is required for a message");
        Preconditions.checkNotNull(this.pushoverUser, "User is required for a message");
        Preconditions.checkNotNull(this.pushoverMessage, "Message is required for a message");
        
        if (Priority.EMERGENCY.equals(this.pushoverPriority)) {
            Preconditions.checkNotNull(this.pushoverRetry, "Retry is required on priority emergency");
            Preconditions.checkNotNull(this.pushoverExpire, "Expire is required on priority emergency");
        }
        
        List<NameValuePair> params = Form.form()
                .add(Constants.TOKEN.get(), this.pushoverToken)
                .add(Constants.USER.get(), this.pushoverUser)
                .add(Constants.MESSAGE.get(), this.pushoverMessage)
                .add(Constants.DEVICE.get(), this.pushoverDevice)
                .add(Constants.TITLE.get(), this.pushoverTitle)
                .add(Constants.URL.get(), this.pushoverUrl)
                .add(Constants.RETRY.get(), this.pushoverRetry)
                .add(Constants.EXPIRE.get(), this.pushoverExpire)
                .add(Constants.CALLBACK.get(), this.pushoverCallback)
                .add(Constants.URLTITLE.get(), this.pushoverUrlTitle)
                .add(Constants.PRIORITY.get(), this.pushoverPriority.get())
                .add(Constants.TIMESTAMP.get(), this.pushoverTimestamp)
                .add(Constants.SOUND.get(), this.pushoverSound.get())
                .build();
        
        HttpResponse httpResponse = null;
        JPushoverResponse jPushoverResponse = null;
        try {
            httpResponse = Request.Post(Constants.MESSAGES_URL.get()).bodyForm(params, Consts.UTF_8).execute().returnResponse();
            
            if (httpResponse != null) {
                int status = httpResponse.getStatusLine().getStatusCode();
                
                jPushoverResponse = new JPushoverResponse()
                    .httpStatus(status)
                    .response(IOUtils.toString(httpResponse.getEntity().getContent(), Consts.UTF_8))
                    .isSuccessful((status == 200) ? true : false);
            }
        } catch (IOException e) {
            LOG.error("Failed to send message to pushover", e);
        }
        
        return (jPushoverResponse == null) ? new JPushoverResponse().isSuccessful(false) : jPushoverResponse;
    }
}