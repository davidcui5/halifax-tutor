package group12.tutorsetting;

import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.request.*;
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

    @PostMapping(path = "/tutor/setting/education", consumes = "application/json", produces = "text/plain")
    public String changeEducation(@RequestBody Map<String, String> body) {
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO, email);
        logger.log(Level.INFO, body.get("education"));
        String education = body.get("education");
        if (tutorSettingDAO.setEducation(email, education)) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    @PostMapping(path = "/tutor/setting/experience", consumes = "application/json", produces = "text/plain")
    public String changeExperience(@RequestBody Map<String, String> body) {
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO, email);
        logger.log(Level.INFO, body.get("experience"));
        String experience = body.get("experience");
        if (tutorSettingDAO.setExperience(email, experience)) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
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
}
