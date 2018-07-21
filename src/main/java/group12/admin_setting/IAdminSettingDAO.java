package group12.admin_setting;

import java.util.List;

public interface IAdminSettingDAO {

    int countAdminByEmail(String email);

    boolean setAdminPassword(String email, String password);

    boolean setSubPlanPrice(int planID, float price);

    boolean setStudentBanStatus(int id, boolean status);

    boolean setTutorBanStatus(int id, boolean status);

    List<ReviewDTO> getReviewsMadeByStudent(int studentID);

    List<ReviewDTO> getReviewsMadeOnTutors(int tutorID);

    boolean deleteReviewByID(int id);
}
