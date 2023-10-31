package de.svenkubiak.jpushover.apis;

import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Future;

import de.svenkubiak.jpushover.enums.Param;
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
public class Glance implements API {
    private NavigableMap<String, String> body = new TreeMap<>();
    private String proxyHost;
    private int proxyPort;
    
    public Glance() {
    }
    
    /**
     * Your application's API token
     * (required)
     *
     * @param token The pushover API token
     * @return Glance instance
     */
    public Glance withToken(String token) {
        Objects.requireNonNull(token, "token can not be null");
        
        body.put(Param.TOKEN.toString(), token);
        return this;
    }
    
    /**
     * The user/group key (not e-mail address) of your user (or you),
     * viewable when logged into the @see <a href="https://pushover.net/login">pushover dashboard</a>
     * (required)
     *
     * @param user The username
     * @return Glance instance
     */
    public Glance withUser(String user) {
        Objects.requireNonNull(user, "user can not be null");
        
        body.put(Param.USER.toString(), user);
        return this;
    }
    
    /**
     * Your user's device name to send the message directly to that device,
     * rather than all of the user's devices
     * (optional)
     *
     * @param device The device name
     * @return Glance instance
     */
    public Glance withDevice(String device) {
        Objects.requireNonNull(device, "device can not be null");
        
        body.put(Param.DEVICE.toString(), device);
        return this;
    }
    
    /**
     * A description of the data being shown, such as "Widgets Sold"
     * 
     * @param title the title to use
     * @return Glance instance
     */
    public Glance withTitle(String title) {
        Objects.requireNonNull(title, "title can not be null");
        Validate.checkArgument(title.length() <= 100, "Title must not exceed a length of 100 characters");
        
        body.put(Param.TITLE.toString(), title);
        return this;
    }
    
    /**
     * The main line of data, used on most screens
     * 
     * @param text the text to use
     * @return Glance instance
     */
    public Glance withText(String text) {
        Objects.requireNonNull(text, "text can not be null");
        Validate.checkArgument(text.length() <= 100, "Text must not exceed a length of 100 characters");
        
        body.put(Param.TEXT.toString(), text);
        return this;
    }
    
    /**
     * A second line of data
     * 
     * @param subtext the subtext to use
     * @return Glance instance
     */
    public Glance withSubtext(String subtext) {
        Objects.requireNonNull(subtext, "subtext can not be null");
        Validate.checkArgument(subtext.length() <= 100, "Subtext must not exceed a length of 100 characters");

        body.put(Param.SUBTEXT.toString(), subtext);
        return this;
    }
    
    /**
     * Shown on smaller screens; useful for simple counts
     * 
     * @param count the count to use
     * @return Glance instance
     */
    public Glance withCount(int count) {
        body.put(Param.COUNT.toString(), String.valueOf(count));
        return this;
    }
    
    /**
     * Shown on some screens as a progress bar/circle
     * 
     * @param percent the percent to use
     * @return GLance instance
     */
    public Glance withPercent(int percent) {
        body.put(Param.PERCENT.toString(), String.valueOf(percent));
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
    public Glance withProxy(String proxyHost, int proxyPort) {
        Objects.requireNonNull(proxyHost, "proxyHost can not be null");
        Validate.checkArgument(proxyPort > 0, "proxyPort must be greater than null");

        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;

        return this;
    }
    
    /**
     * Sends a glance to pushover
     *
     * @return PushoverResponse instance
     * 
     * @throws JPushoverException on failure
     */
    @Override
    public PushoverResponse push() throws JPushoverException {
        Objects.requireNonNull(body.get(Param.TOKEN.toString()), "Token is required for a glance");
        Objects.requireNonNull(body.get(Param.USER.toString()), "User is required for a glance");
        
        return new PushoverRequest().push(Url.GLANCES.toString(), body, this.proxyHost, this.proxyPort);
    }
    
    /**
     * Sends a glance to pushover asynchronously
     *
     * @return PushoverResponse instance
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Future<PushoverResponse> pushAsync() {
        return AsyncService.getInstance().execute(new AsyncExecutor(this));
    }
    
    public String getValue(String param) {
        Objects.requireNonNull(param, "param can not be null");
        
        return body.get(param);
    }
}