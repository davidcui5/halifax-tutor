package group12;

import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;
import group12.tutor_profile.TutorProfileForm;

import java.util.ArrayList;

public interface DatabaseInterface {

    boolean isEmailNew(String email);

    boolean isPhoneNumberNew(String number);

    boolean isCreditCardNew(String creditCardNum);

    boolean authorizeStudent(String email, String password);

    boolean authorizeTutor(String email, String password);

    boolean regStudent(StudentSignupForm student);

    boolean regTutor(TutorSignupForm tutor);

    int getStudentId(String email);

    int getTutorID(String email);

    boolean saveActivationCode(String code);

    boolean activateStudent(int id,String activateCode);

    boolean activateTutor(int id,String activateCode);

    boolean updateStudentPassword(String email, String new_password);

    boolean updateTutorPassword(String email, String new_password);

    TutorProfileForm getTutorProfile(int tutorId);

    String[] getTutorInfo(int tutorId);

    ArrayList<String[]> getTutorCourses(int tutorId);

    int[] getTutorSchedule(int tutorId);

    boolean saveRating(int tutorId,String rating);

    boolean saveFeedback(String studentEmail, TutorProfileForm tutorProfileForm);

    float calculateAverageRating(int tutorId,String rating);

    String[] getCourseFromCourseId(String courseId);

    boolean increaseTotalRating(int tutorId, float ratingCount);

}
