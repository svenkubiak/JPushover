package jpushover.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.svenkubiak.jpushover.utils.Validate;

/**
 * 
 * @author svenkubiak
 *
 */
public class ValidateTest {
    
    @Test
    public void testTrue() {
        Validate.checkArgument(true, "foo");
    }
    
    @Test
    public void testFalse() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Validate.checkArgument(false, "bar");
        });
    }
}