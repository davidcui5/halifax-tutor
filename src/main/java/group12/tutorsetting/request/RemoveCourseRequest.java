package group12.tutorsetting.request;

public class RemoveCourseRequest {
    private String token;
    private String school;
    private String courseCode;

    public RemoveCourseRequest(String token, String school, String courseCode) {
        this.token = token;
        this.school = school;
        this.courseCode = courseCode;
    }

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

    @Override
    public String toString() {
        return "token: " + token + " school: " + school + " courseCode: " + courseCode;
    }
}
