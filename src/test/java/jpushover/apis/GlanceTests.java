package jpushover.apis;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.Glance;
import de.svenkubiak.jpushover.enums.Param;

public class GlanceTests {
    
    @Test
    void testConstruct() {
        //given
        Glance glance = JPushover.newGlance();
        
        //then
        assertTrue(glance instanceof Glance);
    }
    
    @Test
    void testWithToken() {
        //given
        String value = "myToken";
        
        //when
        Glance glance = JPushover.newGlance().withToken(value);
        
        //then
        assertTrue(glance.getValue(Param.TOKEN.toString()).equals(value));
    }
    
    @Test
    void testWithUser() {
        //given
        String value = "myUser";
        
        //when
        Glance glance = JPushover.newGlance().withUser(value);
        
        //then
        assertTrue(glance.getValue(Param.USER.toString()).equals(value));
    }
    
    @Test
    void testWithDevice() {
        //given
        String value = "myDevice";
        
        //when
        Glance glance = JPushover.newGlance().withDevice(value);
        
        //then
        assertTrue(glance.getValue(Param.DEVICE.toString()).equals(value));
    }
    
    @Test
    void testWithTitle() {
        //given
        String value = "myTitle";
        
        //when
        Glance glance = JPushover.newGlance().withTitle(value);
        
        //then
        assertTrue(glance.getValue(Param.TITLE.toString()).equals(value));
    }
    
    @Test
    void testWithText() {
        //given
        String value = "myText";
        
        //when
        Glance glance = JPushover.newGlance().withText(value);
        
        //then
        assertTrue(glance.getValue(Param.TEXT.toString()).equals(value));
    }
    
    @Test
    void testWithSubtext() {
        //given
        String value = "mySubtext";
        
        //when
        Glance glance = JPushover.newGlance().withSubtext(value);
        
        //then
        assertTrue(glance.getValue(Param.SUBTEXT.toString()).equals(value));
    }
    
    @Test
    void testWithCount() {
        //given
        int value = 23;
        
        //when
        Glance glance = JPushover.newGlance().withCount(value);
        
        //then
        assertTrue(glance.getValue(Param.COUNT.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithPercentage() {
        //given
        int value = 42;
        
        //when
        Glance glance = JPushover.newGlance().withPercent(value);
        
        //then
        assertTrue(glance.getValue(Param.PERCENT.toString()).equals(String.valueOf(value)));
    }
}
