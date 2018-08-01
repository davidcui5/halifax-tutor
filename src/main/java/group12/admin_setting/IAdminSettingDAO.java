package group12.admin_setting;

import group12.data_access.Student;
import group12.data_access.Tutor;

import java.util.List;

public interface IAdminSettingDAO {

    int countAdminByEmail(String email);

    boolean setAdminPassword(String email, String password);

    boolean setSubPlanPrice(int planID, float price);

    boolean setStudentBanStatus(int id, boolean status);

    boolean setTutorBanStatus(int id, boolean status);

    boolean deleteReviewByID(int id);

    boolean setTutorRatingAndTotalRatings(float rating, int totalRatings, int tutorID);

    List<ReviewDTO> getReviewsMadeByStudent(int studentID);

    List<ReviewDTO> getReviewsMadeOnTutors(int tutorID);

    Student getStudentByEmail(String email);

    Tutor getTutorByEmail(String email);

    Tutor getTutorByID(int tutorID);

    ReviewDTO getReviewByReviewID(int reviewID);
}
