package group12.registration;

public interface IRegister {

    RegistrationResponse registerStudent(StudentSignupForm student);

    RegistrationResponse registerTutor(TutorSignupForm tutor);

    String activateStudent(int studentID, String activationCode);

    String activateTutor(int tutorID, String activationCode);

}
