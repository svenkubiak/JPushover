package jpushover.http;

import de.svenkubiak.jpushover.http.PushoverResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PushoverResponseTests {

    @Test
    void testConstruct() {
        //given
        String body = UUID.randomUUID().toString();
        PushoverResponse response = PushoverResponse.create();
        response.httpStatus(200);
        response.isSuccessful(true);
        response.limit(10);
        response.remaining(20);
        response.reset(30);
        response.response(body);

        //then
        assertNotNull(response);
        assertEquals(200, response.getHttpStatus());
        assertTrue(response.isSuccessful());
        assertEquals(10, response.getLimit());
        assertEquals(20, response.getRemaining());
        assertEquals(30, response.getReset());
        assertEquals(body, response.getResponse());
    }
}
