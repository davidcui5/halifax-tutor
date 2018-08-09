package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdateEmailRequestTest {
    private UpdateEmailRequest request;

    @Before
    public void testSetup() {
        request = new UpdateEmailRequest();
    }

    @Test
    public void testGetToken() {
        assertNull(request.getToken());
    }

    @Test
    public void testSetToken() {
        String token = "alksjdkfjlsjdf";
        request.setToken(token);
        assertEquals(token, request.getToken());
    }

    @Test
    public void testGetEmail() {
        assertNull(request.getEmail());
    }

    @Test
    public void testSetEmail() {
        String email = "zongming.liu@dal.ca";
        request.setEmail(email);
        assertEquals(email, request.getEmail());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
