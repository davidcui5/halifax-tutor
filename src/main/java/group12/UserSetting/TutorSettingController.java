package group12.UserSetting;

import group12.data_access.GetPlanSQLOperation;
import group12.data_access.Subscribe_Plan;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TutorSettingController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);
    IAccessToken accessToken;
    ITutorSettingDAO tutorSettingDAO;

    public TutorSettingController(){
        accessToken = JWTAccessToken.getInstance();
        tutorSettingDAO = new TutorSettingDAO();
    }

    public TutorSettingController(IAccessToken accessToken, ITutorSettingDAO tutorSettingDAO){
        this.accessToken = accessToken;
        this.tutorSettingDAO = tutorSettingDAO;
    }


    @PostMapping(path="/tutor/setting/password", consumes = "application/json", produces = "text/plain")
    public String changePassword(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("password"));
        IEncryptor encryptor = new SimpleMD5Encryptor();
        String password = encryptor.encrypt(body.get("password"));
        logger.log(Level.INFO,password);
        if(tutorSettingDAO.setTutorPassword(email,password)){
            return SUCCESS;
        }
        else{
            return FAILURE;
        }
    }

    @PostMapping(path = "/tutor/setting/email", consumes = "application/json", produces = "text/plain")
    public  String changeEmail(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("email"));
        String newemail =body.get("email");
        if (tutorSettingDAO.setTutorEmail(email,newemail)){
            return SUCCESS;
        }else {
            return FAILURE;
        }
    }

    @PostMapping(path = "/tutor/setting/card", consumes = "application/json", produces = "text/plain")
    public  String changeCardinfo(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("cardname"));
        logger.log(Level.INFO,body.get("creditCardNumber"));
        logger.log(Level.INFO,body.get("expireDate"));
        logger.log(Level.INFO,body.get("securityCode"));

        String cardname = body.get("cardname");
        String creditCardNumber =body.get("creditCardNumber");
        String expireDate =body.get("expireDate");
        int securityCode = Integer.parseInt(body.get("securityCode"));

        if (tutorSettingDAO.setTutorCard(email,cardname,creditCardNumber,expireDate,securityCode)){
            return SUCCESS;
        }else {
            return FAILURE;
        }
    }

    @PostMapping(path = "/tutor/setting/phone", consumes = "application/json", produces = "text/plain")
    public  String changePhone(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("phone"));
        String phone = body.get("phone");

        if (tutorSettingDAO.setTutorPhone(email,phone)){
            return SUCCESS;
        }else {
            return FAILURE;
        }
    }

    @PostMapping(path="/tutor/setting/education", consumes = "application/json", produces = "text/plain")
    public String changeEducation(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("education"));
        String education =body.get("education");
        if(tutorSettingDAO.setEducation(email,education)){
            return SUCCESS;
        }
        else{
            return FAILURE;
        }
    }

    @PostMapping(path="/tutor/setting/experience", consumes = "application/json", produces = "text/plain")
    public String changeExperience(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("experience"));
        String experience = body.get("experience");
        if(tutorSettingDAO.setExperience(email,experience)){
            return SUCCESS;
        }
        else{
            return FAILURE;
        }
    }


    @PostMapping(path = "/tutor/setting/plan")
    public Map<String,Object> SendPlan() {
        Map<String, Object> map = new HashMap<String, Object>();
        GetPlanSQLOperation getPlanSQLOperation1 = new GetPlanSQLOperation(1);
        Subscribe_Plan plan1 = (Subscribe_Plan) getPlanSQLOperation1.executeMysqlQuery();
        GetPlanSQLOperation getPlanSQLOperation2 = new GetPlanSQLOperation(2);
        Subscribe_Plan plan2 = (Subscribe_Plan) getPlanSQLOperation2.executeMysqlQuery();
        GetPlanSQLOperation getPlanSQLOperation3 = new GetPlanSQLOperation(3);
        Subscribe_Plan plan3 = (Subscribe_Plan) getPlanSQLOperation3.executeMysqlQuery();
        GetPlanSQLOperation getPlanSQLOperation4 = new GetPlanSQLOperation(4);
        Subscribe_Plan plan4 = (Subscribe_Plan) getPlanSQLOperation4.executeMysqlQuery();

        map.put("plan1", plan1);
        map.put("plan2", plan2);
        map.put("plan3", plan3);
        map.put("plan4", plan4);

        return map;
    }
// @PostMapping(path = "/tutor/setting/plan1")
//    public ArrayList<Subscribe_Plan> SendPlan(@RequestBody ArrayList<Subscribe_Plan> plans) {
//        ArrayList<Subscribe_Plan> planArrayList = new ArrayList<Subscribe_Plan>();
//
//        GetPlanSQLOperation getPlanSQLOperation1 = new GetPlanSQLOperation(1);
//        planArrayList.add((Subscribe_Plan) getPlanSQLOperation1.executeMysqlQuery());
//
//        GetPlanSQLOperation getPlanSQLOperation2 = new GetPlanSQLOperation(2);
//        planArrayList.add((Subscribe_Plan) getPlanSQLOperation2.executeMysqlQuery());
//
//        GetPlanSQLOperation getPlanSQLOperation3 = new GetPlanSQLOperation(3);
//        planArrayList.add((Subscribe_Plan) getPlanSQLOperation3.executeMysqlQuery());
//
//        GetPlanSQLOperation getPlanSQLOperation4 = new GetPlanSQLOperation(4);
//        planArrayList.add((Subscribe_Plan) getPlanSQLOperation4.executeMysqlQuery());
//
//        return planArrayList;
//    }
}
