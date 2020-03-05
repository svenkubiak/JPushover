package de.svenkubiak.jpushover.enums;

/**
 * 
 * @author svenkubiak
 *
 */
public enum Priority {
    EMERGENCY("2"),
    HIGH("1"),
    LOW("-1"),
    LOWEST("-2"),
    NORMAL("0");
    
    private final String value;

    Priority (String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}