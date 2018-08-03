package group12.tutorsetting.request;

public class AddCourseRequest extends TutorSettingRequest {
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
        return super.getToken();
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    @Override
    public String toString() {
        return super.toString() + "school: " + school + " courseCode: " + courseCode + " coursePrice: " + coursePrice;
    }
}
