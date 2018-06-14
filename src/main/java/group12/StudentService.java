package group12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IDBDao dao = new DBDAO();

    @Override
    public boolean authorizeStudent(String email, String password) {
        return dao.authorizeStudent(email, password);
    }

    @Override
    public boolean authorizeTutor(String email, String password) {
        return false;
    }

    @Override
    public boolean regStudent(Student student) {
        return false;
    }

    @Override
    public boolean regTutor(Tutor tutor) {
        return false;
    }
}
