package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UpdateCardRequestTest {
    private UpdateCardRequest request;

    @Before
    public void testSetup() {
        request = new UpdateCardRequest();
    }

    @Test
    public void testSetToken() {
        String token = "alksjdkfjlsjdf";
        request.setToken(token);
        assertEquals(token, request.getToken());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
