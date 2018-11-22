package jpushover;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;

/**
 * 
 * @author svenkubiak
 *
 */
public final class MockServer {
    private static WireMockServer wireMockServer;
    private static boolean started;
    
    public MockServer() {
    }
    
    public static void start() {
        if (!started) {
            System.setProperty("mode", "test");
            wireMockServer = new WireMockServer(options()
                    .bindAddress("127.0.0.1")
            );
            wireMockServer.start();
            started = true;
        }
    }
}