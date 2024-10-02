package de.svenkubiak.jpushover.services;

import de.svenkubiak.jpushover.apis.API;
import de.svenkubiak.jpushover.http.PushoverResponse;

import java.util.concurrent.Callable;

public class AsyncExecutor<T> implements Callable<PushoverResponse> {
    private final API api;
    
    public AsyncExecutor(API api) {
        this.api = api;
    }
    
    @Override
    public PushoverResponse call() throws Exception {
        return api.push();
    }
}