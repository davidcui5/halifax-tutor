package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RemoveCourseRequestTest {
    private RemoveCourseRequest request;

    @Before
    public void testSetup() {
        request = new RemoveCourseRequest();
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

    @After
    public void testTeardown() {
        request = null;
    }
}
