package group12.tutorsetting;

import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.request.UpdateEmailRequest;
import group12.tutorsetting.request.UpdatePasswordRequest;
import group12.tutorsetting.request.UpdatePhoneRequest;
import group12.tutorsetting.request.UpdateWeeklyScheduleRequest;
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

    @PostMapping(path = "/tutor/setting/card", consumes = "application/json", produces = "text/plain")
    public String changeCardinfo(@RequestBody Map<String, String> body) {
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO, email);
        logger.log(Level.INFO, body.get("cardname"));
        logger.log(Level.INFO, body.get("creditCardNumber"));
        logger.log(Level.INFO, body.get("expireDate"));
        logger.log(Level.INFO, body.get("securityCode"));

        String cardname = body.get("cardname");
        String creditCardNumber = body.get("creditCardNumber");
        String expireDate = body.get("expireDate");
        int securityCode = Integer.parseInt(body.get("securityCode"));

        if (tutorSettingDAO.setTutorCard(email, cardname, creditCardNumber, expireDate, securityCode)) {
            return SUCCESS;
        } else {
            return FAILURE;
        }
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

//    @PostMapping(path="/tutor/setting/resend", consumes = "application/json", produces = "text/plain")
//    public String resendEmail(@RequestBody Map<String,String> body){
//        String email = accessToken.decodeToken(body.get("token"));
//        logger.log(Level.INFO,email);
//        IMailer mailer = new SpringMailer();
//        mailer.sendMail();
//        if(tutorSettingDAO.setPlan(email,planNo)){
//            return SUCCESS;
//        }
//        else{
//            return FAILURE;
//        }
//    }
//    @PostMapping(path = "/tutor/setting/postplan")
//    public Map<String,Object> SendPlan() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        GetPlanSQLOperation getPlanSQLOperation1 = new GetPlanSQLOperation(1);
//        Subscribe_Plan plan1 = (Subscribe_Plan) getPlanSQLOperation1.executeMysqlQuery();
//        GetPlanSQLOperation getPlanSQLOperation2 = new GetPlanSQLOperation(2);
//        Subscribe_Plan plan2 = (Subscribe_Plan) getPlanSQLOperation2.executeMysqlQuery();
//        GetPlanSQLOperation getPlanSQLOperation3 = new GetPlanSQLOperation(3);
//        Subscribe_Plan plan3 = (Subscribe_Plan) getPlanSQLOperation3.executeMysqlQuery();
//        GetPlanSQLOperation getPlanSQLOperation4 = new GetPlanSQLOperation(4);
//        Subscribe_Plan plan4 = (Subscribe_Plan) getPlanSQLOperation4.executeMysqlQuery();
//
//        map.put("plan1", plan1);
//        map.put("plan2", plan2);
//        map.put("plan3", plan3);
//        map.put("plan4", plan4);
//
//        return map;
//    }

}
