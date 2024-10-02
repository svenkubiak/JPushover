package jpushover.enums;

import de.svenkubiak.jpushover.enums.Param;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParamTests {

    @Test
    void testValues() {
        assertEquals(Param.CALLBACK.toString(), "callback");
        assertEquals(Param.COUNT.toString(), "count");
        assertEquals(Param.DEVICE.toString(), "device");
        assertEquals(Param.EXPIRE.toString(), "expire");
        assertEquals(Param.HTML.toString(), "html");
        assertEquals(Param.MESSAGE.toString(), "message");
        assertEquals(Param.MONOSPACE.toString(), "monospace");
        assertEquals(Param.PERCENT.toString(), "percent");
        assertEquals(Param.PRIORITY.toString(), "priority");
        assertEquals(Param.RETRY.toString(), "retry");
        assertEquals(Param.SOUND.toString(), "sound");
        assertEquals(Param.SUBTEXT.toString(), "subtext");
        assertEquals(Param.TEXT.toString(), "text");
        assertEquals(Param.TIMESTAMP.toString(), "timestamp");
        assertEquals(Param.TITLE.toString(), "title");
        assertEquals(Param.TOKEN.toString(), "token");
        assertEquals(Param.URL.toString(), "url");
        assertEquals(Param.URL_TITLE.toString(), "url_title");
        assertEquals(Param.USER.toString(), "user");
        assertEquals(Param.TTL.toString(), "ttl");
    }
}