package de.svenkubiak.jpushover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        push.callback(CALLBACK);
        assertEquals(push.getCallback(), CALLBACK);

        push.device(DEVICE);
        assertEquals(push.getDevice(), DEVICE);

        push.expire(EXPIRE);
        assertEquals(push.getExpire(), EXPIRE);

        push.message(MESSAGE);
        assertEquals(push.getMessage(), MESSAGE);

        push.priority(Priority.HIGH);
        assertEquals(push.getPriority(), Priority.HIGH);

        push.retry(RETRY);
        assertEquals(push.getRetry(), RETRY);

        push.sound(Sound.ALIEN);
        assertEquals(push.getSound(), Sound.ALIEN);

        push.timestamp(TIMESTAMP);
        assertEquals(push.getTimestamp(), TIMESTAMP);

        push.title(TITLE);
        assertEquals(push.getTitle(), TITLE);

        push.token(TOKEN);
        assertEquals(push.getToken(), TOKEN);

        push.url(URL);
        assertEquals(push.getUrl(), URL);

        push.urlTitle(URL_TITLE);
        assertEquals(push.getUrlTitle(), URL_TITLE);

        push.user(USER);
        assertEquals(push.getUser(), USER);

        push.html();
        assertTrue(push.isHtml());
    }
}