package de.svenkubiak.jpushover;

import de.svenkubiak.jpushover.apis.Glance;
import de.svenkubiak.jpushover.apis.Message;
import de.svenkubiak.jpushover.apis.OpenClient;

/**
 *
 * Zero-dependency convenient class for working with the Pushover API
 * See https://pushover.net/api for API details
 *
 * @author svenkubiak
 *
 */
public class JPushover {
    /**
     * Creates a new Glance instance for the Glances API
     * 
     * @return Glance instance
     */
    public static Glance glanceAPI() {
        return new Glance();
    }
    
    /**
     * Creates a new Message instance for the Messages API
     * 
     * @return Message instance
     */
    public static Message messageAPI() {
        return new Message();
    }
    
    /**
     * Creates a new OpenClient instance for the Open Client API
     * 
     * @return OpenClient instance
     */
    public static OpenClient openClientAPI() {
        return new OpenClient();
    }
}