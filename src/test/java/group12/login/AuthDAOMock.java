package group12.login;

import group12.data_access.Admin;
import group12.data_access.Student;
import group12.data_access.Tutor;

public class AuthDAOMock implements IAuthDAO {
    @Override
    public Student getStudentByEmail(String email) {
        if(email.equals("activeUnbanned@email.com")){
            return new Student("activeUnbanned@email.com","validpass", "ActiveStudent", true, false);
        }
        else if(email.equals("inactiveUnbanned@email.com")){
            return new Student("inactiveUnbanned@email.com","validpass", "InactiveStudent", false, false);
        }
        else if(email.equals("activeBanned@email.com")){
            return new Student("activeBanned@email.com","validpass", "BannedStudent", true, true);
        }
        else if(email.equals("inactiveBanned@email.com")){
            return new Student("inactiveBanned@email.com","validpass", "BannedStudent", false, true);
        }
        else{
            return null;
        }
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        if(email.equals("activeUnbanned@email.com")){
            return new Tutor("activeUnbanned@email.com","validpass", "ActiveTutor", true, false);
        }
        else if(email.equals("inactiveUnbanned@email.com")){
            return new Tutor("inactiveUnbanned@email.com","validpass", "InactiveTutor", false, false);
        }
        else if(email.equals("activeBanned@email.com")){
            return new Tutor("activeBanned@email.com","validpass", "BannedTutor", true, true);
        }
        else if(email.equals("inactiveBanned@email.com")){
            return new Tutor("inactiveBanned@email.com","validpass", "BannedTutor", false, true);
        }
        else{
            return null;
        }
    }

    @Override
    public Admin getAdminByEmail(String email) {
        if(email.equals("valid@email.com")){
            return new Admin("valid@email.com","validpass");
        }
        else{
            return null;
        }
    }
}
