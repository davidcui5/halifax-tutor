package group12.adminsetting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TutorReviewsTest {
    private TutorReviews reviews;

    @Test
    public void testTutorID() {
        reviews = new TutorReviews(1,"abc@dal.ca",null);
        assertEquals(1,reviews.getTutorID());

        reviews.setTutorID(3);
        assertEquals(3,reviews.getTutorID());
    }

    @Test
    public void testTutorEmail() {
        reviews = new TutorReviews(1,"abc@dal.ca",null);
        assertEquals("abc@dal.ca",reviews.getTutorEmail());

        reviews.setTutorEmail("123@dal.ca");
        assertEquals("123@dal.ca",reviews.getTutorEmail());
    }

    @Test
    public void testReviews() {
        List<ReviewDTO> list = new ArrayList<>();
        list.add(new ReviewDTO(1,"hello",5,3));
        reviews = new TutorReviews(1,"abc@dal.ca",list);

        ReviewDTO review = reviews.getReviews().get(0);
        assertEquals(1,review.getReviewID());
        assertEquals("hello",review.getReviewText());
        assertEquals(5,review.getRating(),0.1);
        assertEquals(3,review.getTutorID());

        reviews.setReviews(null);
        assertEquals(null,reviews.getReviews());
    }
}