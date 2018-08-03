package group12.adminsetting;

import group12.tokenauth.IAccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AdminControllerTest {
    private AdminController controller;
    private IAccessToken token;
    private IAdminSettingDAO adminDAO;
    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String UNAUTHORIZED = "UNAUTHORIZED";
    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";

    @Before
    public void setUp() {
        token = new AccessTokenMock();
        adminDAO = new AdminSettingDAOMock();
        controller = new AdminController(token, adminDAO);
    }

    @Test
    public void testAuthorizeAdmin() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        assertEquals(AUTHORIZED,controller.authorizeAdmin(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","invalid@email.com");
        assertEquals(UNAUTHORIZED,controller.authorizeAdmin(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token",null);
        assertEquals(UNAUTHORIZED,controller.authorizeAdmin(input3));
    }

    @Test
    public void testChangePassword() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("password","Qwer1234");
        assertEquals(SUCCESS,controller.changePassword(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","sql failed");
        input2.put("password","sql failed");
        assertEquals(ERROR,controller.changePassword(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token","invalid@email.com");
        input3.put("password","Qwer1234");
        assertEquals(UNAUTHORIZED,controller.changePassword(input3));
    }

    @Test
    public void testChangePrice() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("priceOne","19.99");
        input.put("priceTwo","29.99");
        input.put("priceThree","39.99");
        input.put("priceFour","49.99");
        assertEquals(SUCCESS,controller.changePrice(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","invalid@email.com");
        input.put("priceOne","19.99");
        input.put("priceTwo","29.99");
        input.put("priceThree","39.99");
        input.put("priceFour","49.99");
        assertEquals(UNAUTHORIZED,controller.changePrice(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token","validAdmin@email.com");
        input3.put("priceOne","19.99");
        input3.put("priceTwo","29.99");
        input3.put("priceThree","39.99");
        input3.put("priceFour","-49.99");
        assertEquals(ERROR,controller.changePrice(input3));
    }

    @Test
    public void testBanStudent() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("studentID","111");
        assertEquals(SUCCESS,controller.banStudent(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","invalid@email.com");
        input.put("studentID","111");
        assertEquals(UNAUTHORIZED,controller.banStudent(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token","validAdmin@email.com");
        input3.put("studentID","-111");
        assertEquals(ERROR,controller.banStudent(input3));
    }

    @Test
    public void testUnbanStudent() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("studentID","111");
        assertEquals(SUCCESS,controller.unbanStudent(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","invalid@email.com");
        input.put("studentID","111");
        assertEquals(UNAUTHORIZED,controller.unbanStudent(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token","validAdmin@email.com");
        input3.put("studentID","-111");
        assertEquals(ERROR,controller.unbanStudent(input3));
    }

    @Test
    public void testBanTutor() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("tutorID","333");
        assertEquals(SUCCESS,controller.banTutor(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","invalid@email.com");
        input.put("tutorID","333");
        assertEquals(UNAUTHORIZED,controller.banTutor(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token","validAdmin@email.com");
        input3.put("tutorID","-333");
        assertEquals(ERROR,controller.banTutor(input3));
    }

    @Test
    public void testUnbanTutor() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("tutorID","333");
        assertEquals(SUCCESS,controller.unbanTutor(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","invalid@email.com");
        input.put("tutorID","333");
        assertEquals(UNAUTHORIZED,controller.unbanTutor(input2));

        Map<String, String> input3 = new HashMap<>();
        input3.put("token","validAdmin@email.com");
        input3.put("tutorID","-333");
        assertEquals(ERROR,controller.unbanTutor(input3));
    }

    @Test
    public void testFindStudentReviewsWithValidStudent() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("email","validS111@email.com");
        StudentReviews reviews = controller.findStudentReviews(input);
        assertEquals(111,reviews.getStudentID());
        assertEquals("validS111@email.com",reviews.getStudentEmail());
        ReviewDTO review = reviews.getReviews().get(0);
        assertEquals(15,review.getReviewID());
        assertEquals("s1 review for t5",review.getReviewText());

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","validAdmin@email.com");
        input2.put("email","validS333@email.com");
        StudentReviews reviews2 = controller.findStudentReviews(input2);
        assertEquals(333,reviews2.getStudentID());
        assertEquals("validS333@email.com",reviews2.getStudentEmail());
        assertEquals(null,reviews2.getReviews());
    }

    @Test
    public void testFindStudentReviewsWithInvalidStudent() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("email","invalid@email.com");
        StudentReviews reviews = controller.findStudentReviews(input);
        assertEquals(-1,reviews.getStudentID());
        assertEquals(null,reviews.getStudentEmail());
        assertEquals(null,reviews.getReviews());
    }

    @Test
    public void testFindStudentReviewsAccess() {
        Map<String, String> input = new HashMap<>();
        input.put("token","invalid@email.com");
        input.put("email","validS111@email.com");
        assertEquals(null,controller.findStudentReviews(input));
    }

    @Test
    public void testFindTutorReviewsWithValidTutor() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("email","validT555@email.com");
        TutorReviews reviews = controller.findTutorReviews(input);
        assertEquals(555,reviews.getTutorID());
        assertEquals("validT555@email.com",reviews.getTutorEmail());
        ReviewDTO review = reviews.getReviews().get(0);
        assertEquals(15,review.getReviewID());
        assertEquals("s1 review for t5",review.getReviewText());

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","validAdmin@email.com");
        input2.put("email","validT777@email.com");
        TutorReviews reviews2 = controller.findTutorReviews(input2);
        assertEquals(777,reviews2.getTutorID());
        assertEquals("validT777@email.com",reviews2.getTutorEmail());
        assertEquals(null,reviews2.getReviews());
    }

    @Test
    public void testFindTutorReviewsWithInvalidTutor() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("email","invalid@email.com");
        TutorReviews reviews = controller.findTutorReviews(input);
        assertEquals(-1,reviews.getTutorID());
        assertEquals(null,reviews.getTutorEmail());
        assertEquals(null,reviews.getReviews());
    }

    @Test
    public void testFindTutorReviewsAccess() {
        Map<String, String> input = new HashMap<>();
        input.put("token","invalid@email.com");
        input.put("email","validT555@email.com");
        assertEquals(null,controller.findTutorReviews(input));
    }

    @Test
    public void testDeleteReviewWithValidReview() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("reviewID","15");
        assertEquals(SUCCESS,controller.deleteReview(input));
    }

    @Test
    public void testDeleteReviewWithNonExistentReview() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("reviewID","-15");
        assertEquals(SUCCESS,controller.deleteReview(input));
    }

    @Test
    public void testDeleteReviewWhenSQLFails() {
        Map<String, String> input = new HashMap<>();
        input.put("token","validAdmin@email.com");
        input.put("reviewID","9");
        assertEquals(ERROR,controller.deleteReview(input));

        Map<String, String> input2 = new HashMap<>();
        input2.put("token","validAdmin@email.com");
        input2.put("reviewID","10");
        assertEquals(ERROR,controller.deleteReview(input2));
    }

    @Test
    public void testDeleteReviewAccess() {
        Map<String, String> input = new HashMap<>();
        input.put("token","invalid@email.com");
        input.put("reviewID","15");
        assertEquals(UNAUTHORIZED,controller.deleteReview(input));
    }

    @Test
    public void testCalculateNewRating() {
        float newRating = controller.calculateNewRating(3.0F,2,1.0F);
        assertEquals(5.0F, newRating, 0.0001);

        newRating = controller.calculateNewRating(5.0F,1,5.0F);
        assertEquals(0, newRating, 0.0001);
    }

    @After
    public void tearDown() {
        controller = null;
        token = null;
        adminDAO = null;
    }
}