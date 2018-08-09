package group12.tutorsetting.dataaccess;

import group12.dataaccess.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class TutorSettingDAOImpl implements ITutorSettingDAO {
    private static final Logger logger = LogManager.getLogger(TutorSettingDAOImpl.class);

    private SQLOperationTemplate sqlOperation;
    private SQLDMLOperation sqldmlOperation;
    private IDataAccessObject dataAccessObject;

    @Override
    public boolean updateTutorPassword(String email, String password) {
        sqlOperation = new UpdateTutorPasswordSQLOperation(email, password);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean updateTutorEmail(String email, String newEmail) {
        sqlOperation = new UpdateTutorEmailSQLOperation(email, newEmail);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean updateTutorPhone(String email, String phone) {
        sqlOperation = new UpdateTutorPhoneSQLOperation(email, phone);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean updateTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        sqlOperation = new UpdateTutorCardSQLOperation(email, creditCardHolder, creditCardNum, creditCardExpiryDate, securityCode);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean updateEducation(String email, String education) {
        sqlOperation = new UpdateTutorEducationSQLOperation(email, education);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean updatePhoto(String email, String photoURL) {
        sqldmlOperation = new UpdateTutorPhotoSQLOperation(email, photoURL);
        return sqldmlOperation.executeMysqlUpdate();
    }

    @Override
    public boolean updateExperience(String email, String experience) {
        sqlOperation = new UpdateTutorExperienceSQLOperation(email, experience);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean addCourse(String email, String school, String courseCode, float price) {
        boolean isSuccess;

        dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        List<Course> currentCourses = dataAccessObject.getAllCourses();
        Iterator<Course> iterator = currentCourses.iterator();
        int courseId = 0;
        boolean courseExisted = false;


        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getName().equals(courseCode) && course.getSchool().equals(school)) {
                courseId = course.getId();
                courseExisted = true;
            }
        }

        if (courseExisted) {
            isSuccess = dataAccessObject.setCourseToTutor(tutorId, courseId, price);
        } else {
            Course newCourse = new Course();
            newCourse.setName(courseCode);
            newCourse.setSchool(school);
            isSuccess = dataAccessObject.saveCourse(newCourse);
            if (isSuccess) {
                sqlOperation = new GetCourseIdSQLOperation(courseCode, school);
                courseId = (int) sqlOperation.executeMysqlQuery();
                isSuccess = dataAccessObject.setCourseToTutor(tutorId, courseId, price);
            }
        }
        return isSuccess;
    }

    @Override
    public boolean removeCourse(String email, String school, String courseCode) {
        dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        sqlOperation = new GetCourseIdSQLOperation(courseCode, school);
        int courseId = (int) sqlOperation.executeMysqlQuery();

        sqldmlOperation = new RemoveTutorCourseSQLOperation(tutorId, courseId);
        return sqldmlOperation.executeMysqlUpdate();
    }

    @Override
    public boolean updateWeeklySchedule(String email, WeeklySchedule weeklySchedule) {
        IDataAccessObject dataAccessObject = new MysqlDAOImpl();
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        sqldmlOperation = new UpdateTutorWeeklyScheduleSQLOperation(tutorId, weeklySchedule);
        return sqldmlOperation.executeMysqlUpdate();
    }

    @Override
    public boolean updatePlan(String email, String planNo) {
        sqlOperation = new UpdateTutorSubscriptionSQLOperation(email, planNo);
        return (boolean) sqlOperation.executeMysqlQuery();
    }

    @Override
    public boolean cancelPlan(String email) {
        sqldmlOperation = new CancelTutorSubscriptionSQLOperation(email);
        return sqldmlOperation.executeMysqlUpdate();
    }
}
