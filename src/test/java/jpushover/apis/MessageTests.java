package jpushover.apis;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.Message;
import de.svenkubiak.jpushover.enums.Param;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTests {
    
    @Test
    void testConstruct() {
        //given
        Message message = JPushover.messageAPI();
        
        //then
        assertTrue(message instanceof Message);
    }
    
    @Test
    void testDefaults() {
        //given
        Message message = JPushover.messageAPI();
        
        //then
        assertTrue(message.getValue(Param.PRIORITY.toString()).equals(Priority.NORMAL.toString()));
        assertTrue(message.getValue(Param.SOUND.toString()).equals(Sound.PUSHOVER.toString()));
    }
    
    @Test
    void testWithToken() {
        //given
        String value = "myToken";
        
        //when
        Message message = JPushover.messageAPI().withToken(value);
        
        //then
        assertTrue(message.getValue(Param.TOKEN.toString()).equals(value));
    }
    
    @Test
    void testWithUser() {
        //given
        String value = "myUser";
        
        //when
        Message message = JPushover.messageAPI().withUser(value);
        
        //then
        assertTrue(message.getValue(Param.USER.toString()).equals(value));
    }
    
    @Test
    void testWithTTL() {
        //given
        int ttl = 5;
        
        //when
        Message message = JPushover.messageAPI().withTTL(5);
        
        //then
        assertTrue(message.getValue(Param.TTL.toString()).equals(String.valueOf(ttl)));
    }
    
    @Test
    void testWithRetry() {
        //given
        int value = 3;
        
        //when
        Message message = JPushover.messageAPI().withRetry(value);
        
        //then
        assertTrue(message.getValue(Param.RETRY.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithExpire() {
        //given
        int value = 5;
        
        //when
        Message message = JPushover.messageAPI().withExpire(value);
        
        //then
        assertTrue(message.getValue(Param.EXPIRE.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithMessage() {
        //given
        String value = "myMessage";
        
        //when
        Message message = JPushover.messageAPI().withMessage(value);
        
        //then
        assertTrue(message.getValue(Param.MESSAGE.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithDevice() {
        //given
        String value = "myDevice";
        
        //when
        Message message = JPushover.messageAPI().withDevice(value);
        
        //then
        assertTrue(message.getValue(Param.DEVICE.toString()).equals(value));
    }
    
    @Test
    void testWithTitle() {
        //given
        String value = "myTitle";
        
        //when
        Message message = JPushover.messageAPI().withTitle(value);
        
        //then
        assertTrue(message.getValue(Param.TITLE.toString()).equals(value));
    }
    
    @Test
    void testWithUrl() {
        //given
        String value = "myUrl";
        
        //when
        Message message = JPushover.messageAPI().withUrl(value);
        
        //then
        assertTrue(message.getValue(Param.URL.toString()).equals(value));
    }
    
    @Test
    void testWithUrlTitle() {
        //given
        String value = "myUrlTitle";
        
        //when
        Message message = JPushover.messageAPI().withUrlTitle(value);
        
        //then
        assertTrue(message.getValue(Param.URL_TITLE.toString()).equals(value));
    }
    
    @Test
    void testEnableMonospace() {
        //when
        Message message = JPushover.messageAPI().enableMonospace();
        
        //then
        assertTrue(message.getValue(Param.MONOSPACE.toString()).equals("1"));
        assertTrue(message.getValue(Param.HTML.toString()).equals("0"));
    }
    
    @Test
    void testEnableHtml() {
        //when
        Message message = JPushover.messageAPI().enableHtml();
        
        //then
        assertTrue(message.getValue(Param.MONOSPACE.toString()).equals("0"));
        assertTrue(message.getValue(Param.HTML.toString()).equals("1"));
    }
    
    @Test
    void testWithTimestamp() {
        //given
        int value = 555;
        
        //when
        Message message = JPushover.messageAPI().withTimestamp(value);
        
        //then
        assertTrue(message.getValue(Param.TIMESTAMP.toString()).equals(String.valueOf(value)));
    }
    
    @Test
    void testWithPriority() {
        //given
        Priority value = Priority.EMERGENCY;
        
        //when
        Message message = JPushover.messageAPI().withPriority(value);
        
        //then
        assertTrue(message.getValue(Param.PRIORITY.toString()).equals(value.toString()));
    }
    
    @Test
    void testWithSound() {
        //given
        Sound value = Sound.BUGLE;
        
        //when
        Message message = JPushover.messageAPI().withSound(value);
        
        //then
        assertTrue(message.getValue(Param.SOUND.toString()).equals(value.toString()));
    }
    
    @Test
    void testWithCallback() {
        //given
        String value = "myCallback";
        
        //when
        Message message = JPushover.messageAPI().withCallback(value);
        
        //then
        assertTrue(message.getValue(Param.CALLBACK.toString()).equals(value));
    }
    
    @Test
    void testMissingToken() throws IOException, InterruptedException {
        //given
        String expectedMessage = "Token is required for a message";
        
        //when
        Exception exception = assertThrows(NullPointerException.class, () -> {
            JPushover.messageAPI().push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testMissingUser() throws IOException, InterruptedException {
        //given
        String expectedMessage = "User is required for a message";
        
        //when
        Exception exception = assertThrows(NullPointerException.class, () -> {
            JPushover.messageAPI().withToken("foo").push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testMissingMessage() throws IOException, InterruptedException {
        //given
        String expectedMessage = "Message is required for a message";
        
        //when
        Exception exception = assertThrows(NullPointerException.class, () -> {
            JPushover.messageAPI().withToken("foo").withUser("bar").push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testMessageLimit() throws IOException, InterruptedException {
        //given
        String message = "RiZzOldb0QxPYyVPR0IsbkKnUdw12YEFQng1hb34Yu3xxckX1Jhm8zOEKK4COsdFDq0eE1YBUHlLD3EXks63FPJKRvG8hswsSDBDwi3xvvtbUm2dFMjaHFGvhcT30QKcbItvHqdXyGqa19DSrwEHhHYB7ZdwSsXQmcgscjJMWFTEoANRb5LqWc0a5LWHqesgHP42GccHNCZ03SL3cwa4KUF5H005jMhYv0rWmjqDb7v8voyAThaUQUXpeIMBMnOTOilJUD70DDDWxKZDKj7ymoMApNfvtvRT4sc8ghcWy7KtO0FNWJ5YbKWiXZFpaz7q7KaxzCa5KERGbft2QBoSEIgF0iUTXdp5XkFGXYyQUwOEmlx7I68EhhwOWHO9Qoqi5Nj3B703zbcMDR4BQnlct4Yzqa50bZDahIr7GOAEZrGtzs9IG8BuThWzdZIf64Zg01jVHH8kfCBkg4q9gzBdbARiQ5KmycviBezNgp10JcNE3h4OJBdhmnCm2TO3sXtHbkfznbxhc9sN1FnvVCdnQhBhwAp0TBNzNmbuuebxMccVCyhqjJU1K4XrSQu5Qa3Zlsb2QQ88SeYtQe0tm4a8ojLY9PcdofVGG2EOgNzFCHjgHyja30x6a4CFH71QfG6AkpXfDisYUSB2SxozHEPQKvlyVcfhOla2d9jDbeAAuhtyWW3UhydOspqwaAwXSsKFROfnpzn6izw1eeXqrxxTj08HQuBUpdUB4G8nyQurXFHC2iH6M7Iyb6QZ7WfVLMV5Yn16Pk6AzGVFRLmvMo0yex72vTQqsxNhA15Jjgh1NDqaTJiqLGyRvj9WLWsgop2YwabnwT6MN3Lxa17J3xPg6I1hhnEWkn8GtQmcStOJDOn3nIQC1qq79NUOqfPrY1vGNBfD3cCDEKi7fwoJvBmW8sn04P2UGu9Y0H6lBnklYX5yBk7Je7GKPyYQgxKt72Ij8F9v21eanrVPi8aV39JeMvF8X7ZT0BtqrxE3EG34eYaakRZwiGjl1MwSUFquHoRPE";
        String expectedMessage = "Message can not exceed more than 1024 characters";
        
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            JPushover.messageAPI().withToken("foo").withUser("bar").withMessage(message).push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testTitleLimit() throws IOException, InterruptedException {
        //given
        String title = "XNaH6iAPYS0E2q5ZMAsKbOyAXArfmcWhx1D4HXTt0rmKShR0lLzH87041kw6YNQ5lluOTi5LrWKqQLBe7KuT9JTJLJVEMPGQv7fCN2Wb8aXy57v3g954sVqEQhZmP3xBXL42DMmOMg9iPj4uAGq5S80Bb3omjZTM5IqXa8LhnQbjQVBbNfTrFUQOkjrcLThFj1POURX2iwVvKud0ySNLnDWXLANS2NBt6gUH7L0rihUwEIhwXyvcnr3J3kt";
        String expectedMessage = "Title can not exceed more than 250 characters";
        
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            JPushover.messageAPI().withToken("foo").withUser("bar").withMessage("foobar").withTitle(title).push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testUrlLimit() throws IOException, InterruptedException {
        //given
        String url = "FqP7inUPZBTXO43n6OVbCrDI1FkIEhb8CZDa86pgNV2EgWbArcEgnVTwqpy95DvSr0ZaDs3X624QDTqyygvVl76WkXayt15Mix8jUklgOVHdMjTrnHcsFQLoAu2Tmuq3UzAVw9ekNtXv3GzilNOCuBJPMm3U3bMfu9mZIEZNQ6c1xttFPAbQYGciw4UL8mIX3ZpoqfTtWRtFW4ZQcvgkoUKKxcvR9isx4bBjPDvE8NvjUmrv0kYwUrfl98nCHorYPzRX5RMr2w46MwWhylfw5WTS5M2fpIwjWBoAQH3IBfvExxEhGIjAfCIuBDBh5LlNvW4Blg3j89DV4gmAA9FkfDt2SuWPBu3XuV7pQCz7Mkq4S9uoCp9W7ZceqntQ73WAlkQkO2NQzggPPP3byxljLAMAZk13a7L2J8NMlQc5eOIVkMSdrLnkhklsiLqPZS2Su8aYNveG3IZsLjYjwdfPMZ36t7gkxd7AsjYXDndGfur6FsH7a5MMrAYrQFEYUyRE9";
        String expectedMessage = "URL can not exceed more than 512 characters";
        
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            JPushover.messageAPI().withToken("foo").withUser("bar").withMessage("foobar").withUrl(url).push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    void testUrlTitleLimit() throws IOException, InterruptedException {
        //given
        String urlTitle = "FqP7inUPZBTXO43n6OVbCrDI1FkIEhb8CZDa86pgNV2EgWbArcEgnVTwqpy95DvSr0ZaDs3X624QDTqyygvVl76WkXayt15Mix8jUklgOVHdMjTrnHcsFQLoAu2Tmuq3UzAVw9ekNtXv3GzilNOCuBJPMm3U3bMfu9mZIEZNQ6c1xttFPAbQYGciw4UL8mIX3ZpoqfTtWRtFW4ZQcvgkoUKKxcvR9isx4bBjPDvE8NvjUmrv0kYwUrfl98nCHorYPzRX5RMr2w46MwWhylfw5WTS5M2fpIwjWBoAQH3IBfvExxEhGIjAfCIuBDBh5LlNvW4Blg3j89DV4gmAA9FkfDt2SuWPBu3XuV7pQCz7Mkq4S9uoCp9W7ZceqntQ73WAlkQkO2NQzggPPP3byxljLAMAZk13a7L2J8NMlQc5eOIVkMSdrLnkhklsiLqPZS2Su8aYNveG3IZsLjYjwdfPMZ36t7gkxd7AsjYXDndGfur6FsH7a5MMrAYrQFEYUyRE9";
        String expectedMessage = "URL Title can not exceed more than 100 characters";
        
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            JPushover.messageAPI().withToken("foo").withUser("bar").withMessage("foobar").withUrlTitle(urlTitle).push();
        });
        String actualMessage = exception.getMessage();
     
        //then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
