package group12.tutorsetting.request;

public class AddCourseRequest {
    private String token;
    private String school;
    private String courseCode;
    private float coursePrice;

    public String getSchool() {
        return school;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getToken() {
        return token;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    @Override
    public String toString() {
        return "token: " + token + " school: " + school + " courseCode: " + courseCode + " coursePrice: " + coursePrice;
    }
}
