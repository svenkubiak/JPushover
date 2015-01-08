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
    
    public JPushoverResponse(){
    }

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
     * The raw Json Response from the pushover API
     * @return String
     */
    public String getResponse() {
        return pushoverResponse;
    }

    /**
     * The HTTP status from the HTTP request
     * @return int
     */
    public int getHttpStatus() {
        return pushoverHttpStatus;
    }

    /**
     * True if the request to the pushover API returned HTTP code 200, false otherwise
     * @return boolen
     */
    public boolean isSuccessful() {
        return pushoverSuccessful;
    }
}