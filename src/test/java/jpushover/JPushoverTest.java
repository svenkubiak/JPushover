package jpushover;

import static org.junit.Assert.assertTrue;

import de.svenkubiak.jpushover.JPushover;
import de.svenkubiak.jpushover.apis.Glance;
import de.svenkubiak.jpushover.apis.Message;

/**
 * 
 * @author svenkubiak
 *
 */
public class JPushoverTest {

    public void testNewGlance() {
        assertTrue(JPushover.newGlance() instanceof Glance);
    }
    
    public void testNewMessage() {
        assertTrue(JPushover.newMessage() instanceof Message);
    }
}