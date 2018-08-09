package group12.search.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class IdentityRequestTest {
    private IdentityRequest request;

    @Before
    public void testSetup() {
        request = new IdentityRequest();
    }

    @Test
    public void testSetToken() {
        String oldToken = request.getToken();
        assertEquals(oldToken, request.getToken());

        String newToken = "hahaha";
        request.setToken(newToken);

        assertEquals(newToken, request.getToken());

        assertNotEquals(oldToken, request.getToken());
    }

    @Test
    public void testGetToken() {
        String token = "hahaha";
        request.setToken(token);

        assertEquals(token, request.getToken());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
