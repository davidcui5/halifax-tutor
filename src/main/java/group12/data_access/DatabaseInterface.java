package group12.DBLayer;

import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;

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
}
