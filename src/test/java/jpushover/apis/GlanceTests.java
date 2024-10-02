package jpushover.apis;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.Glance;
import de.svenkubiak.jpushover.enums.Param;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GlanceTests {
    
    @Test
    void testConstruct() {
        //given
        Glance glance = JPushover.glanceAPI();
        
        //then
        assertTrue(glance instanceof Glance);
    }
    
    @Test
    void testWithToken() {
        //given
        String value = "myToken";
        
        //when
        Glance glance = JPushover.glanceAPI().withToken(value);
        
        //then
        assertTrue(glance.getValue(Param.TOKEN.toString()).equals(value));
    }
    
    @Test
    void testWithUser() {
        //given
        String value = "myUser";
        
        //when
        Glance glance = JPushover.glanceAPI().withUser(value);
        
        //then
        assertTrue(glance.getValue(Param.USER.toString()).equals(value));
    }
    
    @Test
    void testWithDevice() {
        //given
        String value = "myDevice";
        
        //when
        Glance glance = JPushover.glanceAPI().withDevice(value);
        
        //then
        assertTrue(glance.getValue(Param.DEVICE.toString()).equals(value));
    }
    
    @Test
    void testWithTitle() {
        //given
        String value = "myTitle";
        
        //when
        Glance glance = JPushover.glanceAPI().withTitle(value);
        
        //then
        assertTrue(glance.getValue(Param.TITLE.toString()).equals(value));
    }
    
    @Test
    void testWithText() {
        //given
        String value = "myText";
        
        //when
        Glance glance = JPushover.glanceAPI().withText(value);
        
        //then
        assertTrue(glance.getValue(Param.TEXT.toString()).equals(value));
    }
    
    @Test
    void testWithSubtext() {
        //given
        String value = "mySubtext";
        
        //when
        Glance glance = JPushover.glanceAPI().withSubtext(value);
        
        //then
        assertTrue(glance.getValue(Param.SUBTEXT.toString()).equals(value));
    }
    
    @Test
    void testWithCount() {
        //given
        int value = 23;
        
        //when
        Glance glance = JPushover.glanceAPI().withCount(value);
        
        //then
        assertTrue(glance.getValue(Param.COUNT.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithPercentage() {
        //given
        int value = 42;
        
        //when
        Glance glance = JPushover.glanceAPI().withPercent(value);
        
        //then
        assertTrue(glance.getValue(Param.PERCENT.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testMissingToken() throws IOException, InterruptedException {
        //given
        String expectedMessage = "Token is required for a glance";
        
        //when
        Exception exception = assertThrows(NullPointerException.class, () -> {
            JPushover.glanceAPI().push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testMissingUser() throws IOException, InterruptedException {
        //given
        String expectedMessage = "User is required for a glance";
        
        //when
        Exception exception = assertThrows(NullPointerException.class, () -> {
            JPushover.glanceAPI().withToken("foo").push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
