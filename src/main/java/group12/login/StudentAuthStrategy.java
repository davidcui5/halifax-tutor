package group12.login;

import group12.data_access.Student;
import group12.data_access.User;

public class StudentAuthStrategy implements IAuthenticationStrategy {

    @Override
    public LoginResponse authenticate(User student) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Student validStudent = authDAO.getStudentByEmail(student.getEmail());
        if(validStudent == null){
            return new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email");
        }
        else if(validStudent.getPassword().equals(student.getPassword())){
            return new LoginResponse(AuthenticationResult.SUCCESS,"Welcome Back, " + validStudent.getFirstName());
        }
        else{
            return new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password");
        }
    }
}
