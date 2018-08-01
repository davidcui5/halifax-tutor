package group12.tutorsetting;


import group12.dataaccess.*;
import group12.dataaccess.tutorsetting.*;

public class TutorSettingDAOImpl implements ITutorSettingDAO {
    private SQLOperationTemplate operation;

    @Override
    public boolean setTutorPassword(String email, String password) {
        operation = new UpdateTutorPasswordSQLOperation(email, password);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean setTutorEmail(String email, String newemail) {
        operation = new UpdateTutorEmailSQLOperation(email, newemail);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean setTutorPhone(String email, String phone) {
        operation = new UpdateTutorPhoneSQLOperation(email, phone);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean setTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        operation = new UpdateTutorCardSQLOperation(email, creditCardHolder, creditCardNum, creditCardExpiryDate, securityCode);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean setEducation(String email, String education) {
        operation = new UpdateTutorEducationSQLOperation(email, education);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean setExperience(String email, String experience) {
        operation = new UpdateTutorExperienceSQLOperation(email, experience);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean addCourse(String email, String school, int code, String price) {
        return false;
    }

    @Override
    public boolean setWeeklySchedule(String email, WeeklySchedule weeklySchedule) {
        IDataAccessObject dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        operation = new UpdateTutorWeeklyScheduleSQLOperation(tutorId, weeklySchedule);
        return (Boolean) ((UpdateTutorWeeklyScheduleSQLOperation) operation).executeMysqlUpdate();
    }

    @Override
    public boolean setPlan(String email, String planNo) {
        operation = new UpdateTutorSbuscriptionSQLOperation(email, planNo);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean cancelPlan(String email) {
        return false;
    }
}
