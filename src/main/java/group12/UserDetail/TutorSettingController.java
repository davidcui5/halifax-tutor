package group12.UserDetail;

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

    public String getUseremail() {
        return useremail;
    }

    @GetMapping(path = "/setting")
    @ResponseBody
    public void setUseremail(@RequestBody String token) {
        try{
            IAccessToken accessToken = new JWTAccessToken();
            //user email,not the new email
            useremail = accessToken.decodeToken(token);

        } catch(Exception e){
            logger.error("ERROR",e);
        }
    }


    @GetMapping(path = "/cemail")
    @ResponseBody
    public TSettingResponse changeEmail(@RequestBody ChangeEmailForm form){
        String email = getUseremail();
        TSettingResponse response = service.changeemail(form,email);
        return response;
    }

}
