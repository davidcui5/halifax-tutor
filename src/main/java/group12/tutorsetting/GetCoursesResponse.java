package group12.tutorsetting;

import group12.dataaccess.Course;

import java.util.ArrayList;
import java.util.List;

public class GetCoursesResponse {
    private boolean success;
    private List<Course> courses;

    public GetCoursesResponse() {
        success = false;
        courses = new ArrayList<>();
    }

    public GetCoursesResponse(boolean success, List<Course> courses) {
        this.success = success;
        this.courses = courses;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
