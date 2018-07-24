package group12.search;

import group12.data_access.TutorPublicInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SearchResponseTest {
    private SearchResponse searchResponse;

    @Before
    public void testSetup() {
        searchResponse = new SearchResponse();
    }

    @Test
    public void testGetSuccess() {
        boolean current = false;
        assertEquals(current, searchResponse.getSuccess());
    }

    @Test
    public void testGetNumOfResults() {
        int numOfResults = 0;
        assertEquals(numOfResults, searchResponse.getNumOfResults());
    }

    @Test
    public void testGetResults() {
        List<TutorPublicInfo> results = new ArrayList<>();
        assertEquals(results, searchResponse.getResults());
    }

    @Test
    public void testSetSuccess() {
        boolean previous = searchResponse.getSuccess();
        boolean success = true;
        searchResponse.setSuccess(success);
        assertEquals(success, searchResponse.getSuccess());
        assertNotEquals(previous, searchResponse.getSuccess());
    }

    @Test
    public void testSetNumOfResults() {
        int previous = searchResponse.getNumOfResults();
        int numOfResults = 10;
        searchResponse.setNumOfResults(numOfResults);
        assertEquals(numOfResults, searchResponse.getNumOfResults());
        assertNotEquals(previous, searchResponse.getNumOfResults());
    }

    @Test
    public void testSetResults() {
        List<TutorPublicInfo> previous = searchResponse.getResults();
        List<TutorPublicInfo> results = new ArrayList<>();
        results.add(new TutorPublicInfo());
        searchResponse.setResults(results);
        assertNotEquals(previous, searchResponse.getResults());
        assertEquals(results, searchResponse.getResults());
    }

    @After
    public void testTeardown() {
        searchResponse = null;
    }
}
