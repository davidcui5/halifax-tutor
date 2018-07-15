package group12.login;

import group12.data_access.Student;
import group12.data_access.User;

public class StudentAuthStrategy implements IAuthenticationStrategy {
    @Override
    public void authenticate(User student) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Student validStudent = authDAO.getStudentByEmail(student.getEmail());
        if(validStudent == null){
            student.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validStudent.getPassword().equals(student.getPassword())){
            student.setLoginResponse(new LoginResponse(AuthenticationResult.SUCCESS,"Welcome Back, " + validStudent.getFirstName()));
        }
        else{
            student.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }
}
