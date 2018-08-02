package group12.tutor_setting;


import group12.data_access.*;

import java.util.Iterator;
import java.util.List;

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
    public boolean addCourse(String email, String school, String courseCode, float price) {
        boolean result = false;

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
            result = dataAccessObject.setCourseToTutor(tutorId, courseId, price);
        } else {
            Course newCourse = new Course();
            newCourse.setName(courseCode);
            newCourse.setSchool(school);
            result = dataAccessObject.saveCourse(newCourse);
            if (result != true) {
                return false;
            } else {
                operation = new GetCourseIdSQLOperation(courseCode, school);
                courseId = (int) operation.executeMysqlQuery();
                result = dataAccessObject.setCourseToTutor(tutorId, courseId, price);
            }
        }
        return result;
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
