package de.svenkubiak.jpushover.http;

/**
 *
 * @author svenkubiak
 *
 */
public class PushoverResponse {
    private String pushoverResponse;
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
}