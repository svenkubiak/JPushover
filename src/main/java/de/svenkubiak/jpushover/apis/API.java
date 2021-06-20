package de.svenkubiak.jpushover.apis;

import de.svenkubiak.jpushover.exceptions.JPushoverException;
import de.svenkubiak.jpushover.http.PushoverResponse;

/**
 * 
 * @author svenkubiak
 *
 */
public interface API {
    PushoverResponse push() throws JPushoverException;
}