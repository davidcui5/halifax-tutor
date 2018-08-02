package group12.adminsetting;

import java.util.List;

public class StudentReviews {
    private int studentID;
    private String studentEmail;
    private List<ReviewDTO> reviews;

    public StudentReviews(int studentID, String studentEmail, List<ReviewDTO> reviews){
        this.studentID = studentID;
        this.studentEmail = studentEmail;
        this.reviews = reviews;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
