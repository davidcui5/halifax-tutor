package group12;


public interface IDBDao {
    boolean authorizeStudent(String email, String password);
    boolean authorizeTutor(String email, String password);
    boolean regStudent(Student student);
    boolean regTutor(Tutor tutor);


}
