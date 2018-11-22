package de.svenkubiak.jpushover.apis;

import java.io.IOException;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

import de.svenkubiak.jpushover.enums.Param;
import de.svenkubiak.jpushover.http.PushoverRequest;
import de.svenkubiak.jpushover.http.PushoverResponse;
import de.svenkubiak.jpushover.utils.Urls;
import de.svenkubiak.jpushover.utils.Validate;

/**
 * 
 * @author svenkubiak
 *
 */
public class Glance {
    private static final String GLANCE_URL = Urls.getGlanceUrl();
    private String token;
    private String user;
    private String device;
    private String title;
    private String text;
    private String subtext;
    private int count;
    private int percent;
    private String proxyHost;
    private int proxyPort;
    
    public Glance withToken(String token) {
        Objects.requireNonNull(token, "token can not be null");
        
        this.token = token;
        return this;
    }
    
    public Glance withUser(String user) {
        Objects.requireNonNull(user, "user can not be null");
        
        this.user = user;
        return this;
    }
    
    public Glance withDevice(String device) {
        Objects.requireNonNull(device, "device can not be null");
        
        this.device = device;
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
        
        this.title = title;
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
        
        this.text = text;
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
        Validate.checkArgument(subtext.length() <= 100, "subtext must not exceed a length of 100 characters");

        this.subtext = subtext;
        return this;
    }
    
    /**
     * Shown on smaller screens; useful for simple counts
     * 
     * @param count the count to use
     * @return Glance instance
     */
    public Glance withCount(int count) {
        this.count = count;
        return this;
    }
    
    /**
     * Shown on some screens as a progress bar/circle
     * 
     * @param percent the percent to use
     * @return GLance instance
     */
    public Glance withPercent(int percent) {
        this.percent = percent;
        return this;
    }
    
    /**
     * Sends a glance to pushover
     *
     * @return PushoverResponse instance
     *
     * @throws IOException if sending the message fails
     * @throws InterruptedException if sending the message fails
     */
    public PushoverResponse push() throws IOException, InterruptedException {
        Objects.requireNonNull(this.token, "Token is required for a glance");
        Objects.requireNonNull(this.user, "User is required for a glance");

        NavigableMap<String, String> body = new TreeMap<>();
        body.put(Param.TOKEN.toString(), this.token);
        body.put(Param.USER.toString(), this.user);
        body.put(Param.DEVICE.toString(), this.device);
        body.put(Param.TITLE.toString(), this.title);
        body.put(Param.TEXT.toString(), this.text);
        body.put(Param.SUBTEXT.toString(), this.subtext);
        body.put(Param.COUNT.toString(), String.valueOf(this.count));
        body.put(Param.PERCENT.toString(), String.valueOf(this.percent));

        
        return new PushoverRequest().push(GLANCE_URL, body, this.proxyHost, this.proxyPort);
    }
}