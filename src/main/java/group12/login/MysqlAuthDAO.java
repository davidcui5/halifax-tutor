package group12.login;

public class MysqlAuthDAO implements IAuthDAO {

    @Override
    public boolean isStudentEmailWrong(String email) {
        return false;
    }

    @Override
    public String getStudentPassword(String email) {
        return "Str";
    }

    @Override
    public boolean isStudentNotActivated(String email) {
        return false;
    }

    @Override
    public boolean isStudentBanned(String email) {
        return false;
    }

    @Override
    public boolean isTutorEmailWrong(String email) {
        return false;
    }

    @Override
    public String getTutorPassword(String email) {
        return "Tut";
    }

    @Override
    public boolean isTutorNotActivated(String email) {
        return false;
    }

    @Override
    public boolean isTutorBanned(String email) {
        return false;
    }

    @Override
    public boolean isAdminEmailWrong(String email) {
        return false;
    }

    @Override
    public String getAdminPassword(String email) {
        return "Admin";
    }
}
