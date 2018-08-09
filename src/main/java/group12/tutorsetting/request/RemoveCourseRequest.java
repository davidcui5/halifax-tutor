package group12.tutorsetting.request;

public class RemoveCourseRequest extends TutorSettingRequest {
    private String school;
    private String courseCode;

    public RemoveCourseRequest() {

    }

    public RemoveCourseRequest(String token, String school, String courseCode) {
        super(token);
        this.school = school;
        this.courseCode = courseCode;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return super.toString() + " school: " + school + " courseCode: " + courseCode;
    }
}
