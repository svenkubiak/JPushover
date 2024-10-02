package jpushover.services;

import de.svenkubiak.jpushover.services.AsyncService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AsyncServiceTests {
    @Test
    void testConstruct() {
        //given
        AsyncService a = AsyncService.getInstance();
        AsyncService b = AsyncService.getInstance();

        //then
        assertNotNull(a);
        assertNotNull(b);
        assertEquals(a, b);
    }
}
