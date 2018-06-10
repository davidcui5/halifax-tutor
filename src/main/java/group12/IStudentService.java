package group12;

public interface IStudentService {
    boolean authorizeStudent(String email, String password);

    boolean authorizeTutor(String email, String password);

    boolean regStudent(Student student);

    boolean regTutor(Tutor tutor);
}
