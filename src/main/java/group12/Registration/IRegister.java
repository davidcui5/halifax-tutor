package group12.Registration;

public interface IRegister {

    RegistrationResponse registerStudent(Student student);

    RegistrationResponse registerTutor(Tutor tutor);

}
