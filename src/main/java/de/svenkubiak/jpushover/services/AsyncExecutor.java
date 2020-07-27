package de.svenkubiak.jpushover.services;

import java.util.concurrent.Callable;

import de.svenkubiak.jpushover.apis.API;
import de.svenkubiak.jpushover.http.PushoverResponse;

/**
 * 
 * @author svenkubiak
 *
 */
public class AsyncExecutor<T> implements Callable<PushoverResponse> {
    private API api;
    
    public AsyncExecutor(API api) {
        this.api = api;
    }
    
    @Override
    public PushoverResponse call() throws Exception {
        return api.push();
    }
}