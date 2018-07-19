package group12.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchRequestTest {
    private SearchRequest searchRequest;

    @Before
    public void testSetup() {
        this.searchRequest = new SearchRequest();
    }

    @Test
    public void testSetSchool() {
        String school = "dalhousie";
        String previousSchool = searchRequest.getSchool();
        searchRequest.setSchool(school);
        assertEquals(searchRequest.getSchool(), school);
        assertNotEquals(searchRequest.getSchool(), previousSchool);
    }

    @Test
    public void testSetCourseName() {
        String courseName = "csci5308";
        String previousCourseName = searchRequest.getCourseName();
        searchRequest.setCourseName(courseName);
        assertEquals(searchRequest.getCourseName(), courseName);
        assertNotEquals(searchRequest.getCourseName(), previousCourseName);
    }

    @Test
    public void testGetSchool() {
        String school = null;
        assertEquals(searchRequest.getSchool(), school);
    }

    @Test
    public void testGetCourseName() {
        String courseName = null;
        assertEquals(searchRequest.getCourseName(), courseName);
    }

    @After
    public void testTeardown() {
        this.searchRequest = null;
    }
}
