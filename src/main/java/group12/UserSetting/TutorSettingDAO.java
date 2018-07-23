package group12.UserSetting;


import group12.data_access.*;

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
        SQLOperationTemplate opeation = new UpdateTutorPhoneSQLOperation(email,phone);
        return (Boolean) opeation.executeMysqlQuery();
    }

    @Override
    public boolean setTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        SQLOperationTemplate operation = new UpdateTutorCardSQLOperation(email,creditCardHolder,creditCardNum,creditCardExpiryDate,securityCode);
        return (Boolean)operation.executeMysqlQuery();
    }

    @Override
    public boolean setEducation(String email, String education) {
        SQLOperationTemplate operation = new UpdateTutorEducationSQLOperation(email,education);
        return (Boolean)operation.executeMysqlQuery();
    }

    @Override
    public boolean setExperience(String email, String experience) {
        SQLOperationTemplate operation = new UpdateTutorExperienceSQLOperation();
        return false;
    }

    @Override
    public boolean addCourse(String email, String school, int code, String price) {
        return false;
    }


}
