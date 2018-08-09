package group12.search.request;

public class SearchRequest {
    private String school;
    private String courseName;

    public void setSchool(String school) {
        this.school = school;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchool() {
        return this.school;
    }

    public String getCourseName() {
        return this.courseName;
    }

    @Override
    public String toString() {
        return "{ school: " + this.school + ", courseName: " + this.courseName + "}";
    }
}
