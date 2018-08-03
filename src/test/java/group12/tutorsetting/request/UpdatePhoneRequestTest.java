package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdatePhoneRequestTest {
    private UpdatePhoneRequest request;

    @Before
    public void testSetup() {
        request = new UpdatePhoneRequest();
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
    public void testGetPhone() {
        assertNull(request.getPhone());
    }

    @Test
    public void testSetPhone() {
        String phone = "123-132-1234";
        request.setPhone(phone);
        assertEquals(phone, request.getPhone());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
