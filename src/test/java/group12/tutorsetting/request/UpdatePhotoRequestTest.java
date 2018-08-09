package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdatePhotoRequestTest {
    private UpdatePhotoRequest request;

    @Before
    public void testSetup() {
        request = new UpdatePhotoRequest();
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
    public void testGetPhoto() {
        assertNull(request.getPhotoURL());
    }

    @Test
    public void testSetPhoto() {
        String url = "fakeUrl";
        request.setPhotoURL(url);
        assertEquals(url, request.getPhotoURL());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
