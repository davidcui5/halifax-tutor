package group12.UserSetting;

import group12.admin_setting.IAdminSettingDAO;
import group12.data_access.GetPlanSQLOperation;
import group12.data_access.Subscribe_Plan;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
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

//    private ITSetting service = new TSettingService();
    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);
//    private String useremail;
    IAccessToken accessToken;

    public TutorSettingController(){
        accessToken = JWTAccessToken.getInstance();
    }

    public TutorSettingController(IAccessToken accessToken){
        this.accessToken = accessToken;
    }


    @PostMapping(path="/access")
    public String authorizeTutor(@RequestBody Map<String,String> body){
        boolean isAuthorized = authorizeAdmin(body.get("token"));
        if(isAuthorized){
            return AUTHORIZED;
        }
        else{
            return UNAUTHORIZED;
        }
    }
    private boolean authorizeAdmin(String token){
        String email = accessToken.decodeToken(token);
        int count = dao.countAdminByEmail(email);
        if(count == 1){
            return true;
        }
        else{
            return false;
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
