package jpushover.enums;

import de.svenkubiak.jpushover.enums.Url;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UrlTests {

    @Test
    void testValues() {
        assertEquals(Url.DELETE.toString(), "https://api.pushover.net/1/devices/###DEVICE_ID###/update_highest_message.json");
        assertEquals(Url.DEVICE.toString(), "https://api.pushover.net/1/devices.json");
        assertEquals(Url.GLANCES.toString(), "https://api.pushover.net/1/glances.json");
        assertEquals(Url.LOGIN.toString(), "https://api.pushover.net/1/users/login.json");
        assertEquals(Url.MESSAGES.toString(), "https://api.pushover.net/1/messages.json");
        assertEquals(Url.VALIDATE.toString(), "https://api.pushover.net/1/users/validate.json");
        assertEquals(Url.WEBSOCKET.toString(), "wss://client.pushover.net/push");
    }
}