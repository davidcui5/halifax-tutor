package group12.tutorprofile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TutorProfileTest {
    TutorProfileService tutorProfileService = new TutorProfileService();

    MockTutorProfileData mockDbda;
    MockMailer mailer;
    MockJWTAccessToken mockToken;

    TutorProfileForm tutorProfileForm = new TutorProfileForm();
    String token;

    @Before
    public void testSetup() {

        mockDbda = new MockTutorProfileData();
        mailer = new MockMailer();
        mockToken = new MockJWTAccessToken();

        tutorProfileService.setDb(mockDbda);
        tutorProfileService.setMailer(mailer);

        token = mockToken.generateToken("rh318779@dal.ca");
    }

    @Test
    public void testPositiveGetTutorProfile(){
        String result = "Success";
        int tutorId = 437;

        assertEquals(result, tutorProfileService.getTutorProfile(tutorId).getResult());
    }

    @Test
    public void testNegativeGetTutorProfile(){
        String result = "Failure";
        int tutorId = 323;

        assertEquals(result, tutorProfileService.getTutorProfile(tutorId).getResult());
    }

    @Test
    public void testSendMessage(){
        tutorProfileForm.setEmail("rh318779@dal.ca");
        tutorProfileForm.setMessage("test send message");

        String result = "Success";

        assertEquals(result, tutorProfileService.sendMessage(tutorProfileForm).getResult());

    }

    @Test
    public void testSendFeedback(){
        tutorProfileForm.setId(437);
        tutorProfileForm.setRating("4");
        tutorProfileForm.setEmail("rahulvala1502@gmail.com");
        tutorProfileForm.setFeedback("test feedback");
        tutorProfileForm.setEmailToken("MOCKTOKEN");
        String result = "Success";

        assertEquals(result, tutorProfileService.sendFeedback(tutorProfileForm).getResult());
    }



}
