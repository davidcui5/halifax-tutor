package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdateWeeklyScheduleRequestTest {
    private UpdateWeeklyScheduleRequest request;

    @Before
    public void testSetup() {
        request = new UpdateWeeklyScheduleRequest();
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
    public void testGetWeeklySchedule() {
        assertNull(request.getWeeklySchedule());
    }

    @Test
    public void testSetWeeklySchedule() {
        boolean[][] schedule = new boolean[7][3];
        request.setWeeklySchedule(schedule);
        assertEquals(schedule, request.getWeeklySchedule());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
