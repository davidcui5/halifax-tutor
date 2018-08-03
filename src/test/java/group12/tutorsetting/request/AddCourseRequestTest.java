package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddCourseRequestTest {
    private AddCourseRequest request;
    private static final float DELTA = 1e-15f;

    @Before
    public void testSetup() {
        request = new AddCourseRequest();
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
    public void testGetSchool() {
        assertNull(request.getSchool());
    }

    @Test
    public void testSetSchool() {
        String school = "DAL";
        request.setSchool(school);
        assertEquals(school, request.getSchool());
    }

    @Test
    public void testGetCourseCode() {
        assertNull(request.getCourseCode());
    }

    @Test
    public void testSetCourseCode() {
        String courseCode = "CSCI5308";
        request.setCourseCode(courseCode);
        assertEquals(courseCode, request.getCourseCode());
    }

    @Test
    public void testGetCoursePrice() {
        assertEquals(0.0f, request.getCoursePrice(), DELTA);
    }

    @Test
    public void testSetCoursePrice() {
        float coursePrice = 4.0f;
        request.setCoursePrice(coursePrice);
        assertEquals(coursePrice, request.getCoursePrice(), DELTA);
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
