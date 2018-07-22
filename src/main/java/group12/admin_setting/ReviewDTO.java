package group12.admin_setting;

public class ReviewDTO {

    private int reviewID;
    private String reviewText;

    public ReviewDTO(int reviewID, String reviewText){
        this.reviewID = reviewID;
        this.reviewText = reviewText;
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
}
