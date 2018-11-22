package de.svenkubiak.jpushover.utils;

/**
 * 
 * @author svenkubiak
 *
 */
public class Urls {
    public static String getGlanceUrl() {
        String mode = System.getProperty("mode");
        if (("test").equals(mode)) {
            return "http://127.0.0.1:8080/1/glances.json";
        }
        
        return "https://api.pushover.net/1/glances.json";
    }
    
    public static String getMessageUrl() {
        String mode = System.getProperty("mode");
        if (("test").equals(mode)) {
            return "http://127.0.0.1:8080/1/messages.json";
        }
        
        return "https://api.pushover.net/1/messages.json";
    }
    
    public static String getValidationUrl() {
        String mode = System.getProperty("mode");
        if (("test").equals(mode)) {
            return "http://127.0.0.1:8080/1/users/validate.json";
        }
        
        return "https://api.pushover.net/1/users/validate.json";
    }
}