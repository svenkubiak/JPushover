package jpushover.exceptions;

import de.svenkubiak.jpushover.exceptions.JPushoverException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JPushoverExceptionTests {

    @Test
    void testValidateTrue() {
        //given
        String message = UUID.randomUUID().toString();
        JPushoverException exception = new JPushoverException(message, new Exception());

        //then
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }
}
