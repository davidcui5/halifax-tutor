package group12.tutor_setting;


import group12.data_access.*;

public class TutorSettingDAOImpl implements ITutorSettingDAO {
    private SQLOperationTemplate operation;
    private IDataAccessObject dataAccessObject;

    @Override
    public boolean updateTutorPassword(String email, String password) {
        operation = new UpdateTutorPasswordSQLOperation(email, password);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean updateTutorEmail(String email, String newEmail) {
        operation = new UpdateTutorEmailSQLOperation(email, newEmail);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean updateTutorPhone(String email, String phone) {
        operation = new UpdateTutorPhoneSQLOperation(email, phone);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean updateTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        operation = new UpdateTutorCardSQLOperation(email, creditCardHolder, creditCardNum, creditCardExpiryDate, securityCode);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean updateEducation(String email, String education) {
        operation = new UpdateTutorEducationSQLOperation(email, education);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean updatePhoto(String email, String photoURL) {
        operation = new UpdateTutorPhotoSQLOperation(email, photoURL);
        return (Boolean) ((UpdateTutorPhotoSQLOperation) operation).executeMysqlUpdate();
    }

    @Override
    public boolean updateExperience(String email, String experience) {
        operation = new UpdateTutorExperienceSQLOperation(email, experience);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean addCourse(String email, String school, String courseCode, String price) {
        dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);
        return false;
    }

    @Override
    public boolean removeCourse(String email, String school, String courseCode) {
        dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        operation = new GetCourseIdSQLOperation(courseCode, school);
        int courseId = (int) operation.executeMysqlQuery();

        operation = new RemoveTutorCourseSQLOperation(tutorId, courseId);
        return ((RemoveTutorCourseSQLOperation) operation).executeMysqlUpdate();
    }

    @Override
    public boolean updateWeeklySchedule(String email, WeeklySchedule weeklySchedule) {
        IDataAccessObject dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        operation = new UpdateTutorWeeklyScheduleSQLOperation(tutorId, weeklySchedule);
        return (Boolean) ((UpdateTutorWeeklyScheduleSQLOperation) operation).executeMysqlUpdate();
    }

    @Override
    public boolean updatePlan(String email, String planNo) {
        operation = new UpdateTutorSubscriptionSQLOperation(email, planNo);
        return (Boolean) operation.executeMysqlQuery();
    }

    @Override
    public boolean cancelPlan(String email) {
        operation = new CancelTutorSubscriptionSQLOperation(email);
        return (boolean) ((CancelTutorSubscriptionSQLOperation) operation).executeMysqlUpdate();
    }
}
