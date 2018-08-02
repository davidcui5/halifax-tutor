package group12.tutor_setting.request;

public class RemoveCourseRequest {
    private String token;
    private String school;
    private String courseCode;

    public void setToken(String token) {
        this.token = token;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getToken() {
        return token;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSchool() {
        return school;
    }
}
