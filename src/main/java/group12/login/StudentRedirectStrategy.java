package group12.login;

import group12.data_access.Student;
import group12.data_access.User;

public class StudentRedirectStrategy implements IRedirectionStrategy{
    @Override
    public void redirect(User student) {
        IAuthDAO authDAO = new MysqlAuthDAO();
        Student validStudent = authDAO.getStudentByEmail(student.getEmail());
        LoginResponse response = student.getLoginResponse();
        if(response.getResult()==AuthenticationResult.SUCCESS){
            if(validStudent.isActivated() && !validStudent.isBanned()){
                response.setUrl("html/search-tutor.html");
            }
            else{
                response.setUrl("html/student-setting-page.html");
            }
        }
        else {
            response.setUrl(".");
        }
        student.setLoginResponse(response);
    }
}
