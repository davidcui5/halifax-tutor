package group12;

import group12.Registration.Student;
import group12.Registration.Tutor;

public interface DatabaseInterface {

    boolean isEmailNew(String email);

    boolean isPhoneNumberNew(String number);

    boolean isCreditCardNew(String creditCardNum);

    boolean authorizeStudent(String email, String password);

    boolean authorizeTutor(String email, String password);

    boolean regStudent(Student student);

    boolean regTutor(Tutor tutor);

    int getStudentId(String email);

    int getTutorID(String email);

    boolean saveActivationCode(String code);

    boolean activateStudent(int id,String activateCode);

    boolean activateTutor(int id,String activateCode);

}
