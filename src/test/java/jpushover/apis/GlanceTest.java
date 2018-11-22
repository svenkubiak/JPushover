package jpushover.apis;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.http.PushoverResponse;
import jpushover.MockServer;

/**
 * 
 * @author svenkubiak
 *
 */
public class GlanceTest {
    private static final String APPLICATION_JSON = "application/json; charset=utf-8";
    private static final String CONTENT_TYPE = "Content-Type";
    
    private GlanceTest () {
        MockServer.start();
    }
    
    @Test()
    public void testTokenRequired() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/glances.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            JPushover.newGlance().push();
        });
    }
    
    @Test()
    public void testUserRequired() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/glances.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        Assertions.assertThrows(NullPointerException.class, () -> {
            JPushover.newGlance().withToken("token").push();
        });
    }
    
    @Test()
    public void testPushWithoutContent() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/glances.json"))
                .willReturn(aResponse()
                    .withStatus(400)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        PushoverResponse response = JPushover.newGlance().withToken("token").withUser("user").push();
        assertFalse(response.isSuccessful());
    }
    
    @Test
    public void testPush() throws IOException, InterruptedException {
        stubFor(post(urlEqualTo("/1/glances.json"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader(CONTENT_TYPE, APPLICATION_JSON)));
        
        PushoverResponse response = JPushover.newGlance().withToken("foo").withUser("bla").withText("foobar").push();
        assertTrue(response.isSuccessful());
    }
}