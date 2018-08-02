package group12.adminsetting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentReviewsTest {
    private StudentReviews reviews;

    @Test
    public void testStudentID() {
        reviews = new StudentReviews(1,"abc@dal.ca",null);
        assertEquals(1,reviews.getStudentID());

        reviews.setStudentID(3);
        assertEquals(3,reviews.getStudentID());
    }

    @Test
    public void testStudentEmail() {
        reviews = new StudentReviews(1,"abc@dal.ca",null);
        assertEquals("abc@dal.ca",reviews.getStudentEmail());

        reviews.setStudentEmail("123@dal.ca");
        assertEquals("123@dal.ca",reviews.getStudentEmail());
    }

    @Test
    public void testReviews() {
        List<ReviewDTO> list = new ArrayList<>();
        list.add(new ReviewDTO(1,"hello",5,3));
        reviews = new StudentReviews(1,"abc@dal.ca",list);

        ReviewDTO review = reviews.getReviews().get(0);
        assertEquals(1,review.getReviewID());
        assertEquals("hello",review.getReviewText());
        assertEquals(5,review.getRating(),0.1);
        assertEquals(3,review.getTutorID());

        reviews.setReviews(null);
        assertEquals(null,reviews.getReviews());
    }
}