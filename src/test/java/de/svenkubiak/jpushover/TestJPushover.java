package de.svenkubiak.jpushover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.UUID;

import org.junit.Test;

import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;

public class TestJPushover {
    private static final String USER = "user";
    private static final String URL_TITLE = "urlTitle";
    private static final String URL = "url";
    private static final String TOKEN = "token";
    private static final String TITLE = "title";
    private static final String TIMESTAMP = "timestamp";
    private static final String RETRY = "retry";
    private static final String MESSAGE = "message";
    private static final String EXPIRE = "expire";
    private static final String DEVICE = "device";
    private static final String CALLBACK = "callback";

    @Test
    public void TestValues(){
        final JPushover push = new JPushover();

        push.withCallback(CALLBACK);
        assertEquals(push.getCallback(), CALLBACK);

        push.withDevice(DEVICE);
        assertEquals(push.getDevice(), DEVICE);

        push.withExpire(EXPIRE);
        assertEquals(push.getExpire(), EXPIRE);

        push.withMessage(MESSAGE);
        assertEquals(push.getMessage(), MESSAGE);

        push.withPriority(Priority.HIGH);
        assertEquals(push.getPriority(), Priority.HIGH);

        push.withRetry(RETRY);
        assertEquals(push.getRetry(), RETRY);

        push.withSound(Sound.ALIEN);
        assertEquals(push.getSound(), Sound.ALIEN);

        push.withTimestamp(TIMESTAMP);
        assertEquals(push.getTimestamp(), TIMESTAMP);

        push.withTitle(TITLE);
        assertEquals(push.getTitle(), TITLE);

        push.withToken(TOKEN);
        assertEquals(push.getToken(), TOKEN);

        push.withUrl(URL);
        assertEquals(push.getUrl(), URL);

        push.withUrlTitle(URL_TITLE);
        assertEquals(push.getUrlTitle(), URL_TITLE);

        push.withUser(USER);
        assertEquals(push.getUser(), USER);

        push.enableHtml();
        assertTrue(push.isHtml());
        
        File file = new File(UUID.randomUUID().toString());
        push.withAttachment(file);
        assertTrue(push.getAttachment() != null);
        
        JPushoverResponse response = push.push();
        assertTrue(response != null);
    }
}