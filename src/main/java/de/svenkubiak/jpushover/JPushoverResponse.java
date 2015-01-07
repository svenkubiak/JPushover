package de.svenkubiak.jpushover;

public class JPushoverResponse {
    private String response;
    private int httpStatus;
    private boolean successful;
    
    public JPushoverResponse(){
    }

    public JPushoverResponse response(String response) {
        this.response = response;
        return this;
    }

    public JPushoverResponse httpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public JPushoverResponse isSuccessful(boolean successful) {
        this.successful = successful;
        return this;
    }

    /**
     * The raw Json Response from the pushover API
     * @return String
     */
    public String getResponse() {
        return response;
    }

    /**
     * The HTTP status from the HTTP request
     * @return int
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * True if request to pushover API returned HTTP code 200, false otherwise
     * @return boolen
     */
    public boolean isSuccessful() {
        return successful;
    }
}