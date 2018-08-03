package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdatePlanRequestTest {
    private UpdatePlanRequest request;

    @Before
    public void testSetup() {
        request = new UpdatePlanRequest();
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
    public void testGetPlan() {
        assertEquals(0, request.getPlanNo());
    }

    @Test
    public void testSetPlan() {
        int planNo = 4;
        request.setPlanNo(planNo);
        assertEquals(planNo, request.getPlanNo());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
