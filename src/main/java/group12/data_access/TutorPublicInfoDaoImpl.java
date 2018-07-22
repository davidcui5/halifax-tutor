package group12.data_access;

import java.util.List;

public class TutorPublicInfoDaoImpl implements TutorPublicInfoDAO {
    @Override
    public List<TutorPublicInfo> getTutorPublicInfo(String school, String courseName) {
        SQLOperationTemplate sqlOperationTemplate = new GetTutorPublicInfoSQLOperation(school, courseName);
        return ((GetTutorPublicInfoSQLOperation) sqlOperationTemplate).executeMysqlQuery();
    }
}
