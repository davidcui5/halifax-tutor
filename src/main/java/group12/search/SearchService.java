package group12.search;

import group12.data_access.TutorPublicInfo;
import group12.data_access.TutorPublicInfoDAO;
import group12.data_access.TutorPublicInfoDaoImpl;
import group12.exceptions.SearchQuerySQLException;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    static SearchResponse getSearchResponse(SearchRequest searchRequest) {
        String school = searchRequest.getSchool();
        String courseName = searchRequest.getCourseName();
        TutorPublicInfoDAO tutorPublicInfoDAO = new TutorPublicInfoDaoImpl();

        boolean success;
        int numOfResults;
        List<TutorPublicInfo> results;

        SearchResponse searchResponse;

        try {
            results = tutorPublicInfoDAO.getTutorPublicInfo(school, courseName);
            success = true;
            numOfResults = results.size();

            searchResponse = new SearchResponse(success, numOfResults, results);
        } catch (SearchQuerySQLException e) {
            results = new ArrayList<>();
            success = false;
            numOfResults = 0;

            searchResponse = new SearchResponse(success, numOfResults, results);
        }

        return searchResponse;
    }
}
