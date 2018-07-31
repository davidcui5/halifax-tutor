package group12.TutorProfile;

import group12.data_access.IDataAccessObject;
import group12.data_access.MysqlDAOImpl;
import group12.email.IMailer;
import group12.email.SpringMailer;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import group12.tutor_profile.TutorProfileForm;
import group12.tutor_profile.TutorProfileService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class TutorProfileTest {
    TutorProfileService tutorProfileService = new TutorProfileService();
    static ClassPathXmlApplicationContext context;
    static IDataAccessObject dbda;
    static IMailer mailer;

    TutorProfileForm tutorProfileForm = new TutorProfileForm();

    @Before
    public void testSetup() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", MysqlDAOImpl.class);
        mailer = context.getBean("mailer", SpringMailer.class);

        tutorProfileService.setDb(dbda);
        tutorProfileService.setMailer(mailer);

        IAccessToken tokenMaker = JWTAccessToken.getInstance();
        tokenMaker.generateToken("rh318779@dal.ca");
    }

    @Test
    public void testPositiveGetTutorProfile(){
        String result = "Success";
        int tutorId = 437;

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
        tutorProfileForm.setEmailToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaDMxODc3OUBkYWwuY2EifQ.o19soOqpFheU57a1wvPcCf6AU8h890G1tJ-7pbPsH-xH98pJPuzA65YzYE1GnBP7ArBg6-ZaYuuzcuq4Pt-_RQ");
        String result = "Success";

        assertEquals(result, tutorProfileService.sendFeedback(tutorProfileForm).getResult());
    }



}
