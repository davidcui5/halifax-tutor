package group12.admin_setting;

public class ReviewDTO {

    private int reviewID;
    private String reviewText;
    private float rating;
    private int tutorID;

    public ReviewDTO(int reviewID, String reviewText){
        this.reviewID = reviewID;
        this.reviewText = reviewText;
    }

    public ReviewDTO(int reviewID, String reviewText, float rating, int tutorID){
        this.reviewID = reviewID;
        this.reviewText = reviewText;
        this.rating = rating;
        this.tutorID = tutorID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }
}
