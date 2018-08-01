package group12.search;

import group12.Configuration;
import group12.data_access.*;
import group12.data_access.TutorPublicInfo;
import group12.data_access.TutorPublicInfoDAO;
import group12.data_access.TutorPublicInfoDaoImpl;
import group12.exceptions.SearchQuerySQLException;
import group12.token_auth.JWTAccessToken;

import java.util.ArrayList;
import java.util.List;

class SearchService {
    private Configuration configuration;

    private TutorPublicInfoDAO tutorPublicInfoDAO;

    public SearchService() {
        tutorPublicInfoDAO = new TutorPublicInfoDaoImpl();
        Configuration.setDb(new MysqlDAOImpl());
        configuration = Configuration.getInstance();
    }

    public SearchService(TutorPublicInfoDAO tutorPublicInfoDAO) {
        this.tutorPublicInfoDAO = tutorPublicInfoDAO;
        Configuration.setDb(new MysqlDAOImpl());
        configuration = Configuration.getInstance();
    }

    SearchResponse getSearchResponse(SearchRequest searchRequest) {
        String school = searchRequest.getSchool();
        String courseName = searchRequest.getCourseName();

        int numOfResults;
        List<TutorPublicInfo> results;

        SearchResponse searchResponse;

        try {
            results = tutorPublicInfoDAO.getTutorPublicInfo(school, courseName);
            numOfResults = results.size();

            searchResponse = new SearchResponse(true, numOfResults, results);
        } catch (SearchQuerySQLException e) {
            results = new ArrayList<>();
            numOfResults = 0;

            searchResponse = new SearchResponse(false, numOfResults, results);
        }

        return searchResponse;
    }

    IdentityResponse getSearchIdentity(IdentityRequest identityRequest) {
        IdentityResponse identityResponse = new IdentityResponse();

        String token = identityRequest.getToken();
        String email = JWTAccessToken.getInstance().decodeToken(token);
        if (email == null) {
            identityResponse.setSuccess(false);
        } else {
            IDataAccessObject dataAccessObject = new MysqlDAOImpl();
            if (dataAccessObject.getStudentByEmail(email) != null) {
                identityResponse.setType("student");
            } else if (dataAccessObject.getTutorByEmail(email) != null) {
                identityResponse.setType("tutor");
            } else {
                identityResponse.setType("admin");
            }
            identityResponse.setSuccess(true);
        }
        return identityResponse;
    }

    NoLoginSearchResponse getNoLoginSearchResponse(NoLoginSearchRequest request) {
        NoLoginSearchResponse response = new NoLoginSearchResponse();
        Boolean auth = configuration.isSearchAuth();
        if (!auth) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
        }
        return response;
    }
}
