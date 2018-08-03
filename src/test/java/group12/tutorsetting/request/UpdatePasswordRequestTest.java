package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdatePasswordRequestTest {
    private UpdatePasswordRequest request;

    @Before
    public void testSetup() {
        request = new UpdatePasswordRequest();
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
    public void testGetPassword() {
        assertNull(request.getPassword());
    }

    @Test
    public void testSetPassword() {
        String password = "slkdjflkdjslkf";
        request.setPassword(password);
        assertEquals(password, request.getPassword());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
