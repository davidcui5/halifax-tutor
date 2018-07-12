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

    @GetMapping(path = "/setting")
    @ResponseBody
    public String gettoken(@RequestBody String token){
        try{
            IAccessToken accessToken = new JWTAccessToken();
            //user email,not the new email
            String useremail = accessToken.decodeToken(token);

            return useremail;
        } catch(Exception e){
            logger.error("ERROR",e);
            return null;
        }
    }

    @GetMapping(path = "/cemail")
    @ResponseBody
    public TSettingResponse changeEmail(@RequestBody ChangeEmailForm form){
        TSettingResponse response = service.changeemail(form);
        return response;
    }

}
