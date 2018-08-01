package group12.tutorsetting;


import group12.dataaccess.tutorsetting.WeeklySchedule;

public interface ITutorSettingDAO {
    boolean updateTutorPassword(String email, String password);
    boolean updateTutorEmail(String email,String newEmail);
    boolean setTutorPhone(String email, String phone);
    boolean setTutorCard(String email, String creditCardHolder,String creditCardNum,String creditCardExpiryDate,int securityCode);
    boolean setEducation(String email, String education);
    boolean setExperience (String email, String experience);
    boolean addCourse(String email,String school,int code, String price);
    boolean updateWeeklySchedule(String email, WeeklySchedule weeklySchedule);
    boolean setPlan(String email, String planNo);
    boolean cancelPlan(String email);
}
