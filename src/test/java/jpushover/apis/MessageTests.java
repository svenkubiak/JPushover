package jpushover.apis;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.Message;
import de.svenkubiak.jpushover.enums.Param;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;

public class MessageTests {
    
    @Test
    void testConstruct() {
        //given
        Message message = JPushover.newMessage();
        
        //then
        assertTrue(message instanceof Message);
    }
    
    @Test
    void testDefaults() {
        //given
        Message message = JPushover.newMessage();
        
        //then
        assertTrue(message.getValue(Param.PRIORITY.toString()).equals(Priority.NORMAL.toString()));
        assertTrue(message.getValue(Param.SOUND.toString()).equals(Sound.PUSHOVER.toString()));
    }
    
    @Test
    void testWithToken() {
        //given
        String value = "myToken";
        
        //when
        Message message = JPushover.newMessage().withToken(value);
        
        //then
        assertTrue(message.getValue(Param.TOKEN.toString()).equals(value));
    }
    
    @Test
    void testWithUser() {
        //given
        String value = "myUser";
        
        //when
        Message message = JPushover.newMessage().withUser(value);
        
        //then
        assertTrue(message.getValue(Param.USER.toString()).equals(value));
    }
    
    @Test
    void testWithRetry() {
        //given
        int value = 3;
        
        //when
        Message message = JPushover.newMessage().withRetry(value);
        
        //then
        assertTrue(message.getValue(Param.RETRY.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithExpire() {
        //given
        int value = 5;
        
        //when
        Message message = JPushover.newMessage().withExpire(value);
        
        //then
        assertTrue(message.getValue(Param.EXPIRE.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithMessage() {
        //given
        String value = "myMessage";
        
        //when
        Message message = JPushover.newMessage().withMessage(value);
        
        //then
        assertTrue(message.getValue(Param.MESSAGE.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithDevice() {
        //given
        String value = "myDevice";
        
        //when
        Message message = JPushover.newMessage().withDevice(value);
        
        //then
        assertTrue(message.getValue(Param.DEVICE.toString()).equals(value));
    }
    
    @Test
    void testWithTitle() {
        //given
        String value = "myTitle";
        
        //when
        Message message = JPushover.newMessage().withTitle(value);
        
        //then
        assertTrue(message.getValue(Param.TITLE.toString()).equals(value));
    }
    
    @Test
    void testWithUrl() {
        //given
        String value = "myUrl";
        
        //when
        Message message = JPushover.newMessage().withUrl(value);
        
        //then
        assertTrue(message.getValue(Param.URL.toString()).equals(value));
    }
    
    @Test
    void testWithUrlTitle() {
        //given
        String value = "myUrlTitle";
        
        //when
        Message message = JPushover.newMessage().withUrlTitle(value);
        
        //then
        assertTrue(message.getValue(Param.URL_TITLE.toString()).equals(value));
    }
    
    @Test
    void testEnableMonospace() {
        //when
        Message message = JPushover.newMessage().enableMonospace();
        
        //then
        assertTrue(message.getValue(Param.MONOSPACE.toString()).equals("1"));
        assertTrue(message.getValue(Param.HTML.toString()).equals("0"));
    }
    
    @Test
    void testEnableHtml() {
        //when
        Message message = JPushover.newMessage().enableHtml();
        
        //then
        assertTrue(message.getValue(Param.MONOSPACE.toString()).equals("0"));
        assertTrue(message.getValue(Param.HTML.toString()).equals("1"));
    }
    
    @Test
    void testWithTimestamp() {
        //given
        int value = 555;
        
        //when
        Message message = JPushover.newMessage().withTimestamp(value);
        
        //then
        assertTrue(message.getValue(Param.TIMESTAMP.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithPriority() {
        //given
        Priority value = Priority.EMERGENCY;
        
        //when
        Message message = JPushover.newMessage().withPriority(value);
        
        //then
        assertTrue(message.getValue(Param.PRIORITY.toString()).equals(value.toString()));
    }
    
    @Test
    void testWithSound() {
        //given
        Sound value = Sound.BUGLE;
        
        //when
        Message message = JPushover.newMessage().withSound(value);
        
        //then
        assertTrue(message.getValue(Param.SOUND.toString()).equals(value.toString()));
    }
    
    @Test
    void testWithCallback() {
        //given
        String value = "myCallback";
        
        //when
        Message message = JPushover.newMessage().withCallback(value);
        
        //then
        assertTrue(message.getValue(Param.CALLBACK.toString()).equals(value));
    }
}
