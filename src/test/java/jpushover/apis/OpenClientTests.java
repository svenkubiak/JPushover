package jpushover.apis;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.OpenClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenClientTests {
    @Test
    void testConstruct() {
        //given
        OpenClient openClient = JPushover.openClientAPI();

        //then
        assertNotNull(openClient);
    }
}
