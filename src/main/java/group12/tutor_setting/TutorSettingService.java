package group12.tutor_setting;

import group12.data_access.Course;
import group12.data_access.IDataAccessObject;
import group12.data_access.MysqlDAOImpl;
import group12.data_access.WeeklySchedule;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import group12.tutor_setting.request.*;

import java.util.List;

class TutorSettingService {
    private ITutorSettingDAO tutorSettingDAO = new TutorSettingDAOImpl();
    private IDataAccessObject dataAccessObject = new MysqlDAOImpl();
    private IAccessToken accessToken = JWTAccessToken.getInstance();

    public void setAccessToken(IAccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setTutorSettingDAO(ITutorSettingDAO tutorSettingDAO) {
        this.tutorSettingDAO = tutorSettingDAO;
    }

    TutorSettingResponse getUpdateWeeklyScheduleResponse(UpdateWeeklyScheduleRequest updateWeeklyScheduleRequest) {
        String token = updateWeeklyScheduleRequest.getToken();
        String email = accessToken.decodeToken(token);

        boolean[][] schedule = updateWeeklyScheduleRequest.getWeeklySchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule(schedule);

        boolean success = tutorSettingDAO.updateWeeklySchedule(email, weeklySchedule);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePasswordResponse(UpdatePasswordRequest updatePasswordRequest) {
        String token = updatePasswordRequest.getToken();
        String password = updatePasswordRequest.getPassword();
        IEncryptor encryptor = SimpleMD5Encryptor.getInstance();

        password = encryptor.encrypt(password);

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorPassword(email, password);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateEmailResponse(UpdateEmailRequest updateEmailRequest) {
        String token = updateEmailRequest.getToken();

        String email = accessToken.decodeToken(token);

        String newEmail = updateEmailRequest.getEmail();

        boolean success = tutorSettingDAO.updateTutorEmail(email, newEmail);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePhoneResponse(UpdatePhoneRequest updatePhoneRequest) {
        String token = updatePhoneRequest.getToken();
        String phone = updatePhoneRequest.getPhone();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorPhone(email, phone);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateCardResponse(UpdateCardRequest updateCardRequest) {
        String token = updateCardRequest.getToken();
        String holderName = updateCardRequest.getHolderName();
        String creditCardNumber = updateCardRequest.getCreditCardNumber();
        String expiryDate = updateCardRequest.getExpireDate();
        int securityCode = updateCardRequest.getSecurityCode();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorCard(email, holderName, creditCardNumber, expiryDate, securityCode);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getResendConfirmationEmailResponse(ResendConfirmationRequest resendConfirmationRequest) {
        String token = resendConfirmationRequest.getToken();

        String email = accessToken.decodeToken(token);

        // TODO: Resend confirmation email.

        return new TutorSettingResponse(true);
    }

    TutorSettingResponse getUpdateEducationResponse(UpdateEducationRequest updateEducationRequest) {
        String token = updateEducationRequest.getToken();
        String education = updateEducationRequest.getEducation();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateEducation(email, education);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePhotoResponse(UpdatePhotoRequest updatePhotoRequest) {
        String token = updatePhotoRequest.getToken();
        String photoURL = updatePhotoRequest.getPhotoURL();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updatePhoto(email, photoURL);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateExperienceResponse(UpdateExperienceRequest updateExperienceRequest) {
        String token = updateExperienceRequest.getToken();
        String experience = updateExperienceRequest.getExperience();

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

    TutorSettingResponse getCancelSubscriptionResponse(CancelSubscriptionRequest request) {
        String token = request.getToken();
        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.cancelPlan(email);
        return new TutorSettingResponse(success);
    }

    GetCoursesResponse getGetCoursesResponse(GetCoursesRequest request) {
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
