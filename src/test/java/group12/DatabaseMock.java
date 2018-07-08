package group12;

import group12.registration.Student;
import group12.registration.Tutor;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMock implements DatabaseInterface {
    public boolean isEmailNew(String email){
        if(email.equals("zongming.liu@dal.ca"))
            return false;
        else
            return true;
    }

    public boolean isPhoneNumberNew(String number){
        if (number.equals("6049700746"))
            return false;
        else
            return true;
    };

    public boolean isCreditCardNew(String creditCardNum){
        if (creditCardNum.equals("1111222233334444"))
            return false;
        else
            return true;
    };

    public boolean authorizeStudent(String email, String password){
        return true;
    }

    public boolean authorizeTutor(String email, String password){
        return true;
    }

    public boolean regStudent(Student student){
        return true;
    }

    public boolean regTutor(Tutor tutor){
        return true;
    }

    @Override
    public int getStudentId(String email) {
        return 0;
    }

    @Override
    public int getTutorID(String email) {
        return 0;
    }

    @Override
    public boolean saveActivationCode(String code) {
        return false;
    }

    @Override
    public boolean activateStudent(int id, String activateCode) {
        return false;
    }

    @Override
    public boolean activateTutor(int id, String activateCode) {
        return false;
    }

}
