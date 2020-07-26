package de.svenkubiak.jpushover.http;

/**
 *
 * @author svenkubiak
 *
 */
public class PushoverResponse {
    private String pushoverResponse;
    private long pushoverLimit;
    private long pushoverRemaining;
    private long pushoverReset;
    private int pushoverHttpStatus;
    private boolean pushoverSuccessful;
    
    public PushoverResponse response(String response) {
        this.pushoverResponse = response;
        return this;
    }

    public PushoverResponse httpStatus(int httpStatus) {
        this.pushoverHttpStatus = httpStatus;
        return this;
    }

    public PushoverResponse isSuccessful(boolean successful) {
        this.pushoverSuccessful = successful;
        return this;
    }
    
    public PushoverResponse limit(long limit) {
        this.pushoverLimit = limit;
        return this;
    }
    
    public PushoverResponse remaining(long remaining) {
        this.pushoverRemaining = remaining;
        return this;
    }
    
    public PushoverResponse reset(long reset) {
        this.pushoverReset = reset;
        return this;
    }

    /**
     * @return The pushover response
     */
    public String getResponse() {
        return pushoverResponse;
    }

    /**
     * @return The HTTP status
     */
    public int getHttpStatus() {
        return pushoverHttpStatus;
    }

    /**
     * @return true if the API returned a HTTP status code 200, false otherwise
     */
    public boolean isSuccessful() {
        return pushoverSuccessful;
    }

    /**
     * @return The API rate limit
     */
    public long getLimit() {
        return pushoverLimit;
    }

    /**
     * @return The remaining allowed API requests
     */
    public long getRemaining() {
        return pushoverRemaining;
    }

    /**
     * @return The API rate limit reset timestamp
     */
    public long getReset() {
        return pushoverReset;
    }
}