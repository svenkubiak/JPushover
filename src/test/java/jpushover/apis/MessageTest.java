package jpushover.apis;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.Message;
import de.svenkubiak.jpushover.enums.Priority;
import de.svenkubiak.jpushover.enums.Sound;
import de.svenkubiak.jpushover.http.PushoverResponse;
import jpushover.MockServer;

/**
 * 
 * @author svenkubiak
 *
 */
public class MessageTest {
    private static final String APPLICATION_JSON = "application/json; charset=utf-8";
    private static final String CONTENT_TYPE = "Content-Type";
    
    private MessageTest () {
        MockServer.start();
    }
    
    @Test()
    public void testTokenRequired() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/messages.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            JPushover.newMessage().push();
        });
    }
    
    @Test()
    public void testUserRequired() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/messages.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            JPushover.newMessage().withToken("token").push();
        });
    }
    
    @Test()
    public void testMessageRequired() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/messages.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            JPushover.newMessage().withToken("token").withUser("user").push();
        });
    }
    
    @Test()
    public void testPushWithoutContent() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/messages.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        PushoverResponse response = JPushover.newMessage().withToken("token").withUser("user").withMessage("").push();
        assertFalse(response.isSuccessful());
    }
    
    @Test
    public void testPush() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/messages.json"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        PushoverResponse response = JPushover.newMessage().withToken("foo").withUser("bla").withMessage("foobar").push();
        assertTrue(response.isSuccessful());
    }
    
    @Test
    public void testConstruct() throws IOException, InterruptedException {
        //given
        int proxyPort = 8080;
        int timestamp = 0;
        int expire = 0;
        int retry = 0;
        String callback = "callback";
        String device = "device";
        String message = "message";
        String user = "user";
        String urlTitle = "urlTitle";
        String proxyHost = "proxyhost";
        String title = "tile";
        String token = "token";
        String url = "https://www.url.url";
        Priority priority = Priority.HIGH;
        Sound sound = Sound.BUGLE;
        
       //when
       Message m = JPushover.newMessage()
                .enableHtml()
                .withCallback(callback)
                .withDevice(device)
                .withExpire(expire)
                .withMessage(message)
                .withPriority(priority)
                .withProxy(proxyHost, proxyPort)
                .withRetry(retry)
                .withSound(sound)
                .withTimestamp(timestamp)
                .withTitle(title)
                .withToken(token)
                .withUrl(url)
                .withUser(user);
        
       //then
       assertThat(m.isHtml(), equalTo(true));
       assertThat(m.isMonospace(), equalTo(false));
       assertThat(m.getCallback(), equalTo(callback));
       assertThat(m.getDevice(), equalTo(device));
       assertThat(m.getExpire(), equalTo(expire));
       assertThat(m.getMessage(), equalTo(message));
       assertThat(m.getPriority(), equalTo(Priority.HIGH));
       assertThat(m.getProxyHost(), equalTo(proxyHost));
       assertThat(m.getProxyPort(), equalTo(proxyPort));
       assertThat(m.getRetry(), equalTo(retry));
       assertThat(m.getSound(), equalTo(Sound.BUGLE));
       assertThat(m.getTimestamp(), equalTo(timestamp));
       assertThat(m.getTitle(), equalTo(title));
       assertThat(m.getToken(), equalTo(token));
       assertThat(m.getUrl(), equalTo(url));
       assertThat(m.getUrlTitle(), equalTo(url));
       assertThat(m.getUser(), equalTo(user));
       
       //when
       m.withUrlTitle(urlTitle);
       m.enableMonospace();
       
       //then
       assertThat(m.getUrlTitle(), equalTo(urlTitle));
       assertThat(m.isHtml(), equalTo(false));
       assertThat(m.isMonospace(), equalTo(true));
    }
}
