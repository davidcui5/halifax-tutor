package group12.tutor_setting;

import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import group12.tutor_setting.request.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TutorSettingController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);
    private IAccessToken accessToken;
    private ITutorSettingDAO tutorSettingDAO;
    private TutorSettingService tutorSettingService;

    public TutorSettingController() {
        accessToken = JWTAccessToken.getInstance();
        tutorSettingDAO = new TutorSettingDAOImpl();
        tutorSettingService = new TutorSettingService();
    }

    public TutorSettingController(IAccessToken accessToken, ITutorSettingDAO tutorSettingDAO) {
        this.accessToken = accessToken;
        this.tutorSettingDAO = tutorSettingDAO;
    }


    @PostMapping(path = "/tutor/setting/password", headers = "content-type=application/json")
    public TutorSettingResponse updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdatePasswordResponse(updatePasswordRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/email", headers = "content-type=application/json")
    public TutorSettingResponse updateEmail(@RequestBody UpdateEmailRequest updateEmailRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateEmailResponse(updateEmailRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/card", headers = "content-type=application/json")
    public TutorSettingResponse updateCard(@RequestBody UpdateCardRequest updateCardRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateCardResponse(updateCardRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/phone", headers = "content-type=application/json")
    public TutorSettingResponse updatePhone(@RequestBody UpdatePhoneRequest updatePhoneRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdatePhoneResponse(updatePhoneRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/education", headers = "content-type=application/json")
    public TutorSettingResponse updateEducation(@RequestBody UpdateEducationRequest updateEducationRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateEducationResponse(updateEducationRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/experience", headers = "content-type=application/json")
    public TutorSettingResponse updateExperience(@RequestBody UpdateExperienceRequest updateExperienceRequest) {
        TutorSettingResponse tutorSettingResponse = tutorSettingService.getUpdateExperienceResponse(updateExperienceRequest);
        return tutorSettingResponse;
    }

    @PostMapping(path = "/tutor/setting/weeklySchedule", headers = "content-type=application/json")
    public TutorSettingResponse updateWeeklySchedule(@RequestBody UpdateWeeklyScheduleRequest updateWeeklyScheduleRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateWeeklyScheduleResponse(updateWeeklyScheduleRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/plan", consumes = "application/json", produces = "text/plain")
    public String changePlan(@RequestBody Map<String, String> body) {
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO, email);
        String planNo = body.get("planNo");

        if (tutorSettingDAO.setPlan(email, planNo)) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    @PostMapping(path = "/tutor/setting/cancel", consumes = "application/json", produces = "text/plain")
    public String cancelPlan(@RequestBody Map<String, String> body) {
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO, email);

        if (tutorSettingDAO.cancelPlan(email)) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    @PostMapping(path = "/tutor/setting/resend", headers = "content-type=application/json")
    public TutorSettingResponse resendConfirmationEmail(@RequestBody ResendConfirmationRequest resendConfirmationRequest) {
        TutorSettingResponse tutorSettingResponse = tutorSettingService.getResendConfirmationEmailResponse(resendConfirmationRequest);
        return tutorSettingResponse;
    }

    @PostMapping(path = "/tutor/setting/photo", headers = "content-type=application/json")
    public TutorSettingResponse updateProfilePicture(@RequestBody UpdatePhotoRequest updatePhotoRequest) {
        TutorSettingResponse tutorSettingResponse = tutorSettingService.getUpdatePhotoResponse(updatePhotoRequest);
        return tutorSettingResponse;
    }
}