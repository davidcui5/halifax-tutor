package group12.UserSetting;


public interface ITutorSettingDAO {
    int countTutorByEmail(String email);
    boolean setTutorPassword(String email, String password);
    boolean setTutorEmail(String email);
    boolean setTutorPhone(String email, String phone);
    boolean setTutorCard(String email, String creditCardHolder,String creditCardNum,String creditCardExpiryDate,int securityCode);
    boolean setEdu(String email, String education);
    boolean setExperience (String email, String experience);
    boolean addCourse(String email,String school,int code, String price);

}
