package de.svenkubiak.jpushover.apis;

import java.io.IOException;

import de.svenkubiak.jpushover.http.PushoverResponse;

/**
 * 
 * @author svenkubiak
 *
 */
public interface API {
    PushoverResponse push() throws IOException, InterruptedException;
}