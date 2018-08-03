package group12.search.mock;

import group12.dataaccess.GetTutorPublicInfoSQLOperation;
import group12.dataaccess.SQLOperationTemplate;

import java.util.List;

public class TutorPublicInfoDaoImpl implements TutorPublicInfoDAO {
    @Override
    public List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName) {
        SQLOperationTemplate sqlOperationTemplate = new GetTutorPublicInfoSQLOperation(school, courseName);
        return (List<TutorPublicInfo>) sqlOperationTemplate.executeMysqlQuery();
    }
}
