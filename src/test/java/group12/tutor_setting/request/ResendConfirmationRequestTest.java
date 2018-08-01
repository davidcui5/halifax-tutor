package group12.tutor_setting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResendConfirmationRequestTest {
    private ResendConfirmationRequest request;

    @Before
    public void testSetup() {
        request = new ResendConfirmationRequest();
    }

    @Test
    public void testSetToken() {
        String token = "aaa";
        request.setToken(token);
        assertEquals(token, request.getToken());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
