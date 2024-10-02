package de.svenkubiak.jpushover.services;

import de.svenkubiak.jpushover.http.PushoverResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncService<T> {
    private static AsyncService<?> INSTANCE;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    
    @SuppressWarnings("rawtypes")
    public static AsyncService<?> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AsyncService();
        }
        
        return INSTANCE;
    }
    
    public Future<PushoverResponse> execute(AsyncExecutor<PushoverResponse> asyncExecutor) {
        return executorService.submit(asyncExecutor);
    }
    
    public void shutdown() {
        executorService.shutdown();
    }
}