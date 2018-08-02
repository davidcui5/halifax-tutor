package group12.dataaccess;

import group12.exceptions.SearchQuerySQLException;

import java.util.List;

public class TutorPublicInfoDaoImpl implements TutorPublicInfoDAO {
    @Override
    public List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName) throws SearchQuerySQLException {
        SQLOperationTemplate sqlOperationTemplate = new GetTutorPublicInfoSQLOperation(school, courseName);
        return ((GetTutorPublicInfoSQLOperation) sqlOperationTemplate).executeSearchQuery();
    }
}
