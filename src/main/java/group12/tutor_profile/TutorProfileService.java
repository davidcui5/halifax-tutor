package group12.tutor_profile;

import group12.DatabaseInterface;
import group12.email.IMailer;
import group12.registration.RegistrationService;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class TutorProfileService implements iTutorProfile {

    private DatabaseInterface db;
    private IMailer mailer;
    private static Logger logger = LogManager.getLogger(RegistrationService.class);
    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;

    public void setDb(DatabaseInterface db) {
        this.db = db;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    @Override
    public TutorProfileResponse getTutorProfile(int tutorId) {

        TutorProfileForm tutorProfileForm = db.getTutorProfile(tutorId);
        TutorProfileResponse response = new TutorProfileResponse();

        if (tutorProfileForm != null){

            response.setPhotoURL(tutorProfileForm.getPhotoURL());
            response.setFirstName(tutorProfileForm.getFirstName());
            response.setLastName(tutorProfileForm.getLastName());
            response.setPhoneNumber(tutorProfileForm.getPhoneNumber());
            response.setEmail(tutorProfileForm.getEmail());
            response.setEducation(tutorProfileForm.getEducation());
            response.setExperience(tutorProfileForm.getExperience());
            response.setRating(tutorProfileForm.getRating());
            response.setCourseList(tutorProfileForm.getCourseList());
            response.setTutorSchedule(tutorProfileForm.getTutorSchedule());

            response.setResult("Success");
        }
        else{
            response.setResult("Failure");
            response.addDetail("Internal Server Error");
        }
        return response;
    }

    @Override
    public TutorProfileResponse sendMessage(TutorProfileForm tutorProfileForm) {
        TutorProfileResponse response = new TutorProfileResponse();

        String studentEmail = JWTAccessToken.getInstance().decodeToken(tutorProfileForm.getEmailToken());
        String emailBody = "Message From: " + studentEmail + " " + tutorProfileForm.getMessage();

        mailer.sendMail(emailSender, tutorProfileForm.getEmail(), "Message From Student", emailBody);

        response.setResult("Success");

        return response;
    }

    @Override
    public TutorProfileResponse sendFeedback(TutorProfileForm tutorProfileForm) {
        TutorProfileResponse response = new TutorProfileResponse();
        String studentEmail = JWTAccessToken.getInstance().decodeToken(tutorProfileForm.getEmailToken());
        studentEmail = "newstu5@dal.ca"; // TODO: Remove

        if(db.saveRating(tutorProfileForm.getId(), tutorProfileForm.getRating()) && db.saveFeedback(studentEmail, tutorProfileForm)){
            String emailBody = "Student : " + studentEmail + "  Sent you Feedback: " + tutorProfileForm.getFeedback();
            mailer.sendMail(emailSender, tutorProfileForm.getEmail(), "Message From Student", emailBody);

            response.setResult("Success");
        }
        else{
            response.setResult("Failure");
            response.addDetail("Internal Server Error");
        }

        return response;
    }


}
