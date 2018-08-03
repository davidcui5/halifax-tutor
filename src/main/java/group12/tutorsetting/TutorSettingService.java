package group12.tutorsetting;

import group12.dataaccess.Course;
import group12.dataaccess.IDataAccessObject;
import group12.dataaccess.MysqlDAOImpl;
import group12.dataaccess.WeeklySchedule;
import group12.email.IMailer;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.dataaccess.ITutorSettingDAO;
import group12.tutorsetting.dataaccess.TutorSettingDAOImpl;
import group12.tutorsetting.request.*;
import group12.tutorsetting.response.GetCoursesResponse;
import group12.tutorsetting.response.TutorSettingResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

class TutorSettingService {
    private static final Logger logger = LogManager.getLogger(TutorSettingService.class);

    private ITutorSettingDAO tutorSettingDAO;
    private IDataAccessObject dataAccessObject;
    private IAccessToken accessToken;

    private IMailer mailer;

    @Value("${email.sender}")
    private String emailSender;

    @Value("${server.url}")
    private String serverURL;

    public TutorSettingService() {
        tutorSettingDAO = new TutorSettingDAOImpl();
        dataAccessObject = new MysqlDAOImpl();
        accessToken = JWTAccessToken.getInstance();
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }

    public void setAccessToken(IAccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setTutorSettingDAO(ITutorSettingDAO tutorSettingDAO) {
        this.tutorSettingDAO = tutorSettingDAO;
    }

    public void setDataAccessObject(IDataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    TutorSettingResponse getUpdateWeeklyScheduleResponse(UpdateWeeklyScheduleRequest request) {
        String token = request.getToken();
        String email = accessToken.decodeToken(token);

        boolean[][] schedule = request.getWeeklySchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule(schedule);

        boolean success = tutorSettingDAO.updateWeeklySchedule(email, weeklySchedule);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePasswordResponse(UpdatePasswordRequest request) {
        String token = request.getToken();
        String password = request.getPassword();
        IEncryptor encryptor = SimpleMD5Encryptor.getInstance();

        password = encryptor.encrypt(password);

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorPassword(email, password);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateEmailResponse(UpdateEmailRequest request) {
        String token = request.getToken();

        String email = accessToken.decodeToken(token);

        String newEmail = request.getEmail();

        boolean success = tutorSettingDAO.updateTutorEmail(email, newEmail);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePhoneResponse(UpdatePhoneRequest request) {
        String token = request.getToken();
        String phone = request.getPhone();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorPhone(email, phone);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateCardResponse(UpdateCardRequest request) {
        String token = request.getToken();
        String holderName = request.getHolderName();
        String creditCardNumber = request.getCreditCardNumber();
        String expiryDate = request.getExpireDate();
        int securityCode = request.getSecurityCode();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorCard(email, holderName, creditCardNumber, expiryDate, securityCode);

        return new TutorSettingResponse(success);
    }

    // Got from Zaher's code
    private boolean isAuthorized(String token) throws Exception {
        try {
            boolean result = false;
            String email = accessToken.decodeToken(token);
            int count = dataAccessObject.countOfUserWithEmail(email);
            if (count == 1) {
                result = true;
            }
            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new Exception(ex);
        }
    }

    TutorSettingResponse getResendConfirmationEmailResponse(TutorSettingRequest request) {
        String token = request.getToken();
        boolean success = false;

        // Got from Zaher's code
        try {
            if (isAuthorized(token)) {
                String email = accessToken.decodeToken(token);
                int tutorId = dataAccessObject.getTutorIDByEmail(email);
                UUID uuid = UUID.randomUUID();
                dataAccessObject.saveActivationCode(uuid.toString());
                mailer.sendMail(emailSender, email, "Activation",
                        "Activation " + serverURL + "/tutor/tutorid/" + tutorId + "/activation/" + uuid.toString() + "/");
                success = true;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            success = false;
        }
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateEducationResponse(UpdateEducationRequest request) {
        String token = request.getToken();
        String education = request.getEducation();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateEducation(email, education);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePhotoResponse(UpdatePhotoRequest request) {
        String token = request.getToken();
        String photoURL = request.getPhotoURL();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updatePhoto(email, photoURL);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateExperienceResponse(UpdateExperienceRequest request) {
        String token = request.getToken();
        String experience = request.getExperience();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateExperience(email, experience);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePlanResponse(UpdatePlanRequest request) {
        String token = request.getToken();
        String planNo = Integer.toString(request.getPlanNo());

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updatePlan(email, planNo);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getCancelSubscriptionResponse(TutorSettingRequest request) {
        String token = request.getToken();
        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.cancelPlan(email);
        return new TutorSettingResponse(success);
    }

    GetCoursesResponse getGetCoursesResponse(TutorSettingRequest request) {
        String token = request.getToken();
        String email = accessToken.decodeToken(token);
        int tutorId = dataAccessObject.getTutorIDByEmail(email);

        List<Course> courses = dataAccessObject.getCoursesOFTutor(tutorId);

        return new GetCoursesResponse(true, courses);
    }

    TutorSettingResponse getRemoveCourseResponse(RemoveCourseRequest request) {
        String token = request.getToken();
        String school = request.getSchool();
        String courseName = request.getCourseCode();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.removeCourse(email, school, courseName);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getAddCourseResponse(AddCourseRequest request) {
        String token = request.getToken();
        String school = request.getSchool();
        String courseCode = request.getCourseCode();
        float coursePrice = request.getCoursePrice();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.addCourse(email, school, courseCode, coursePrice);

        return new TutorSettingResponse(success);
    }
}
