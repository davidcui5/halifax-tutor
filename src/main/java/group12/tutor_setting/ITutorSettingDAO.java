package group12.tutor_setting;


import group12.data_access.tutor.WeeklySchedule;

public interface ITutorSettingDAO {
    boolean setTutorPassword(String email, String password);
    boolean setTutorEmail(String email,String newemail);
    boolean setTutorPhone(String email, String phone);
    boolean setTutorCard(String email, String creditCardHolder,String creditCardNum,String creditCardExpiryDate,int securityCode);
    boolean setEducation(String email, String education);
    boolean setExperience (String email, String experience);
    boolean addCourse(String email,String school,int code, String price);
    boolean setWeeklySchedule(String email, WeeklySchedule weeklySchedule);
    boolean setPlan(String email, String planNo);
    boolean cancelPlan(String email);
}
