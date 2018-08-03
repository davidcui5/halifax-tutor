package group12.search;

import group12.Configuration;
import group12.dataaccess.*;
import group12.search.mock.TutorPublicInfo;
import group12.search.mock.TutorPublicInfoDAO;
import group12.search.mock.TutorPublicInfoDaoImpl;
import group12.search.request.IdentityRequest;
import group12.search.request.SearchRequest;
import group12.search.response.IdentityResponse;
import group12.search.response.NoLoginSearchResponse;
import group12.search.response.SearchResponse;
import group12.search.response.Type;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

class SearchService {
    private static final Logger logger = LogManager.getLogger(SearchService.class);
    private Configuration configuration;
    private TutorPublicInfoDAO tutorPublicInfoDAO;
    private IDataAccessObject dataAccessObject;
    private IAccessToken accessToken;

    public SearchService() {
        tutorPublicInfoDAO = new TutorPublicInfoDaoImpl();
        Configuration.setDb(new MysqlDAOImpl());
        configuration = Configuration.getInstance();
        accessToken = JWTAccessToken.getInstance();
        dataAccessObject = new MysqlDAOImpl();
    }

    public SearchService(TutorPublicInfoDAO tutorPublicInfoDAO, IAccessToken accessToken, IDataAccessObject dataAccessObject) {
        this.tutorPublicInfoDAO = tutorPublicInfoDAO;
        Configuration.setDb(new MysqlDAOImpl());
        configuration = Configuration.getInstance();
        this.accessToken = accessToken;
        this.dataAccessObject = dataAccessObject;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    SearchResponse getSearchResponse(SearchRequest request) {
        String school = request.getSchool();
        String courseName = request.getCourseName();

        int numOfResults;
        List<TutorPublicInfo> results;
        SearchResponse searchResponse;

        try {
            results = tutorPublicInfoDAO.getTutorPublicInfo(school, courseName);
            numOfResults = results.size();
            searchResponse = new SearchResponse(true, numOfResults, results);
        } catch (Exception e) {
            logger.error(e.getMessage());
            results = null;
            numOfResults = 0;
            searchResponse = new SearchResponse(false, numOfResults, results);
        }
        return searchResponse;
    }

    IdentityResponse getSearchIdentity(IdentityRequest request) {
        IdentityResponse identityResponse = new IdentityResponse();

        String token = request.getToken();
        String email = accessToken.decodeToken(token);
        if (email == null) {
            identityResponse.setSuccess(false);
        } else {
            if (dataAccessObject.getStudentByEmail(email) != null) {
                identityResponse.setType(Type.STUDENT);
            } else if (dataAccessObject.getTutorByEmail(email) != null) {
                identityResponse.setType(Type.TUTOR);
            } else {
                identityResponse.setType(Type.ADMIN);
            }
            identityResponse.setSuccess(true);
        }
        return identityResponse;
    }

    NoLoginSearchResponse getNoLoginSearchResponse() {
        NoLoginSearchResponse response = new NoLoginSearchResponse();
        boolean auth = configuration.isSearchAuth();
        if (!auth) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }
}
