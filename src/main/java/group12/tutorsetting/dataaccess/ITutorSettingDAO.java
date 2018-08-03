package group12.tutorsetting.dataaccess;

import group12.dataaccess.WeeklySchedule;

public interface ITutorSettingDAO {
    boolean updateTutorPassword(String email, String password);

    boolean updateTutorEmail(String email, String newEmail);

    boolean updateTutorPhone(String email, String phone);

    boolean updateTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode);

    boolean updateEducation(String email, String education);

    boolean updatePhoto(String email, String photoURL);

    boolean updateExperience(String email, String experience);

    boolean addCourse(String email, String school, String courseCode, float price);

    boolean removeCourse(String email, String school, String courseCode);

    boolean updateWeeklySchedule(String email, WeeklySchedule weeklySchedule);

    boolean updatePlan(String email, String planNo);

    boolean cancelPlan(String email);
}
