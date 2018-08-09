package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdateExperienceRequestTest {
    private UpdateExperienceRequest request;

    @Before
    public void testSetup() {
        request = new UpdateExperienceRequest();
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
    public void testGetExperience() {
        assertNull(request.getExperience());
    }

    @Test
    public void testSetExperience() {
        String experience = "TA for CS 1 and 2";
        request.setExperience(experience);
        assertEquals(experience, request.getExperience());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
