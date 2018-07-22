package group12.UserSetting;


import group12.data_access.SQLOperationTemplate;
import group12.data_access.UpdateTutorCardSQLOperation;
import group12.data_access.UpdateTutorEmailSQLOperation;
import group12.data_access.UpdateTutorPasswordSQLOperation;

public class TutorSettingDAO implements ITutorSettingDAO{

    @Override
    public boolean setTutorPassword(String email, String password) {
        SQLOperationTemplate operation = new UpdateTutorPasswordSQLOperation(email,password);
        return (Boolean)operation.executeMysqlQuery();
    }

    @Override
    public boolean setTutorEmail(String email,String newemail) {
        SQLOperationTemplate operation = new UpdateTutorEmailSQLOperation(email,newemail);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean setTutorPhone(String email, String phone) {
        return false;
    }

    @Override
    public boolean setTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        SQLOperationTemplate operation = new UpdateTutorCardSQLOperation(email,creditCardHolder,creditCardNum,creditCardExpiryDate,securityCode);
        return (Boolean)operation.executeMysqlQuery();
    }

    @Override
    public boolean setEducation(String email, String education) {
        return false;
    }

    @Override
    public boolean setExperience(String email, String experience) {
        return false;
    }

    @Override
    public boolean addCourse(String email, String school, int code, String price) {
        return false;
    }
}
