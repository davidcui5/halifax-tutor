package group12;

public interface DatabaseInterface {

    boolean isEmailNew(String email);

    boolean isPhoneNumberNew(String number);

    boolean isCreditCardNew(String creditCardNum);

    boolean authorizeStudent(String email, String password);

    boolean authorizeTutor(String email, String password);

    boolean regStudent(Student student);

    boolean regTutor(Tutor tutor);

}
