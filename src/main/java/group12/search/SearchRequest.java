package group12.search;

public class SearchRequest {
    private String school;
    private String courseName;

    void setSchool(String school) {
        this.school = school;
    }

    void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    String getSchool() {
        return this.school;
    }

    String getCourseName() {
        return this.courseName;
    }

    @Override
    public String toString() {
        return "{ school: " + this.school + ", courseName: " + this.courseName + "}";
    }
}
