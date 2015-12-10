package de.svenkubiak.jpushover;

/**
 *
 * @author svenkubiak
 *
 */
public class JPushoverResponse {
    private String pushoverResponse;
    private int pushoverHttpStatus;
    private boolean pushoverSuccessful;

    public JPushoverResponse response(String response) {
        this.pushoverResponse = response;
        return this;
    }

    public JPushoverResponse httpStatus(int httpStatus) {
        this.pushoverHttpStatus = httpStatus;
        return this;
    }

    public JPushoverResponse isSuccessful(boolean successful) {
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
     * @return true if the api returned a HTTP status code 200, false othwise
     */
    public boolean isSuccessful() {
        return pushoverSuccessful;
    }
}