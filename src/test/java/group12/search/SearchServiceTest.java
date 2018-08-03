package group12.search;

import group12.Configuration;
import group12.dataaccess.IDataAccessObject;
import group12.search.mock.*;
import group12.search.request.IdentityRequest;
import group12.search.request.SearchRequest;
import group12.search.response.IdentityResponse;
import group12.search.response.NoLoginSearchResponse;
import group12.search.response.SearchResponse;
import group12.tokenauth.IAccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchServiceTest {
    private SearchService service;

    @Before
    public void testSetup() {
        TutorPublicInfoDAO dao = new TutorPublicInfoDAOMock();
        IAccessToken accessToken = new JWTAccessTokenMock();
        IDataAccessObject dataAccessObject = new DataAccessObjectMock();
        service = new SearchService(dao, accessToken, dataAccessObject);
        Configuration.setDb(new DataAccessObjectMock());
        service.setConfiguration(Configuration.getInstance());
    }

    @Test
    public void testGetSearchResponse() {
        String invalidSchool = "SUP";
        String invalidCourseName = "SUP101";

        SearchRequest request = new SearchRequest();
        request.setSchool(invalidSchool);
        request.setCourseName(invalidCourseName);

        SearchResponse response = service.getSearchResponse(request);
        assertTrue(response.getSuccess());
        assertEquals(response.getNumOfResults(), 0);

        String validSchool = "DAL";
        String validCourseName = "CSCI5308";

        request.setSchool(validSchool);
        request.setCourseName(validCourseName);

        response = service.getSearchResponse(request);
        assertTrue(response.getSuccess());
        assertEquals(response.getNumOfResults(), 1);
    }

    @Test
    public void testGetSearchIdentity() {
        String invalidToken = "lskjdfkj";
        IdentityRequest request = new IdentityRequest();
        request.setToken(invalidToken);
        IdentityResponse response = service.getSearchIdentity(request);
        assertFalse(response.isSuccess());

        String validToken = "token";
        request.setToken(validToken);
        response = service.getSearchIdentity(request);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testGetNoLoginSearchResponse() {
        // Given in the mock of DataAccessObject, the auth is required, thus the response contains false.
        NoLoginSearchResponse response = service.getNoLoginSearchResponse();
        assertFalse(response.isSuccess());
    }

    @After
    public void testTeardown() {
        service = null;
    }
}
