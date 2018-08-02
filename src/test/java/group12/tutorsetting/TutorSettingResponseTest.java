package group12.tutorsetting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TutorSettingResponseTest {
    private TutorSettingResponse tutorSettingResponse;

    @Before
    public void testSetup() {
        this.tutorSettingResponse = new TutorSettingResponse();
    }

    @Test
    public void testGetSuccess() {
        assertFalse(tutorSettingResponse.isSuccess());
    }

    @Test
    public void testSetSuccess() {
        tutorSettingResponse.setSuccess(true);
        assertTrue(tutorSettingResponse.isSuccess());
    }

    @After
    public void testTeardown() {
        this.tutorSettingResponse = null;
    }
}
