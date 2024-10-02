package jpushover.utils;

import de.svenkubiak.jpushover.utils.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateTests {
    private static final String THIS_IS_AN_ERROR_MESSAGE = "This is an error message";

    @Test
    void testValidateFalse() {
        //given
        String expectedMessage = THIS_IS_AN_ERROR_MESSAGE;
        boolean value = false;
        
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Validate.checkArgument(value, THIS_IS_AN_ERROR_MESSAGE);
        });
        String actualMessage = exception.getMessage();
        
        //then
        assertTrue(actualMessage.equals(expectedMessage));
    }
    
    @Test
    void testValidateTrue() {
        //given
        boolean value = true;
        
        //then
        Validate.checkArgument(value, THIS_IS_AN_ERROR_MESSAGE);
    }
}