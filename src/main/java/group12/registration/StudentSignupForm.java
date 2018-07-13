package group12.registration;

public class StudentSignupForm extends UserSignupForm {

    private String school;

    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return super.toString() + "School: " + school;
    }
}
