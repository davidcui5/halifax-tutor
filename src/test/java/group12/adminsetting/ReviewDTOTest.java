package group12.adminsetting;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewDTOTest {
    private ReviewDTO review;

    @Test
    public void testReviewID() {
        review = new ReviewDTO(1,"hello");
        assertEquals(1,review.getReviewID());
        review.setReviewID(3);
        assertEquals(3,review.getReviewID());
    }

    @Test
    public void testReviewText() {
        review = new ReviewDTO(1, "hello");
        assertEquals("hello",review.getReviewText());
        review.setReviewText("bonjour");
        assertEquals("bonjour",review.getReviewText());
    }

    @Test
    public void testRating() {
        review = new ReviewDTO(1,null,5.0F,3);
        assertEquals(5.0F,review.getRating(),0.1);
        review.setRating(3);
        assertEquals(3.0F,review.getRating(),0.1);
    }

    @Test
    public void testTutorID() {
        review = new ReviewDTO(1,null,5.0F,3);
        assertEquals(3,review.getTutorID());
        review.setTutorID(10);
        assertEquals(10,review.getTutorID());
    }
}