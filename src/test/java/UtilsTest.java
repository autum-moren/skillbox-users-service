import autum.com.users.utils.Generator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    @Test
    public void generateIdentifierTest() {
        var result = Generator.generateIdentifier();
        assertNotNull(result);
        assertEquals(32, result.length());
        assertTrue(result.matches("^\\w*$"));
    }

}
