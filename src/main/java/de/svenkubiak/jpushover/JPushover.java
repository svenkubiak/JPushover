package de.svenkubiak.jpushover;

import de.svenkubiak.jpushover.apis.Glance;
import de.svenkubiak.jpushover.apis.Message;

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
    public static Glance newGlance() {
        return new Glance();
    }
    
    /**
     * Creates a new Message instance for the Messages API
     * 
     * @return Message instance
     */
    public static Message newMessage() {
        return new Message();
    }
}