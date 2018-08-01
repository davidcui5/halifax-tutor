package group12.admin_setting;

import java.util.List;

public class TutorReviews {
    private int tutorID;
    private String tutorEmail;
    private List<ReviewDTO> reviews;

    public TutorReviews(int tutorID, String tutorEmail, List<ReviewDTO> reviews){
        this.tutorID = tutorID;
        this.tutorEmail = tutorEmail;
        this.reviews = reviews;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public void setTutorEmail(String tutorEmail) {
        this.tutorEmail = tutorEmail;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}