package group12.UserSetting;

import group12.admin_setting.IAdminSettingDAO;
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

import java.util.Map;

@RestController
@RequestMapping(path="/tutor/setting")
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


    @PostMapping(path="/password")
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


    //get token from frontend
    @GetMapping(path = "/setting")
    @ResponseBody
    public void setUseremail(@RequestBody String token) {
        try{
            IAccessToken accessToken = JWTAccessToken.getInstance();
            useremail = accessToken.decodeToken(token);
        } catch(Exception e){
            logger.error("ERROR",e);
        }
    }


    @GetMapping(path = "/cemail")
    @ResponseBody
    public TSettingResponse ChangeEmail(@RequestBody ChangeEmailForm form){
        String email = useremail;
        TSettingResponse response = service.changeEmail(form,email);
        return response;
    }


    @GetMapping(path = "/cpwd")
    @ResponseBody
    public TSettingResponse ChangePwd(@RequestBody ChangePwdForm form){
        String email = useremail;
        TSettingResponse response = service.changePwd(form,email);
        return response;
    }

    @GetMapping(path = "/cphone")
    @ResponseBody
    public TSettingResponse ChangePhone(@RequestBody ChangePhoneForm form){
        String email = useremail;
        TSettingResponse response = service.changePhone(form,email);
        return response;
    }

    @GetMapping(path = "/ccard")
    @ResponseBody
    public TSettingResponse ChangeCard(@RequestBody ChangeCardForm form){
        String email = useremail;
        TSettingResponse response = service.changeCard(form,email);
        return response;
    }


}
