package group12.dataaccess.search;

import group12.exceptions.SearchQuerySQLException;

import java.util.List;

public interface TutorPublicInfoDAO {
    List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName) throws SearchQuerySQLException;
}
