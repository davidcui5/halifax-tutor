package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdateEducationRequestTest {
    private UpdateEducationRequest request;

    @Before
    public void testSetup() {
        request = new UpdateEducationRequest();
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
    public void testGetEducation() {
        assertNull(request.getEducation());
    }

    @Test
    public void testSetEducation() {
        String education = "Dalhousie University";
        request.setEducation(education);
        assertEquals(education, request.getEducation());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
