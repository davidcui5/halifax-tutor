package group12.UserSetting;

import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
public class TutorSettingController {

    private ITSetting service = new TSettingService();
    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);

    private String useremail;

    //get token from frontend
    @GetMapping(path = "/setting")
    @ResponseBody
    public void setUseremail(@RequestBody String token) {
        try{
            IAccessToken accessToken = new JWTAccessToken();
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
}
