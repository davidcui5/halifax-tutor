package group12.search.response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoLoginSearchResponseTest {
    private NoLoginSearchResponse response;

    @Before
    public void testSetup() {
        response = new NoLoginSearchResponse();
    }

    @Test
    public void testGetSuccess() {
        assertFalse(response.isSuccess());
        response.setSuccess(true);
        assertTrue(response.isSuccess());

        response = new NoLoginSearchResponse(true);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testSetSuccess() {
        response.setSuccess(true);
        assertTrue(response.isSuccess());
    }

    @After
    public void testTeardown() {
        response = null;
    }
}
