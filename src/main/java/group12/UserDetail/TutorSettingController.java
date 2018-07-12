package group12.UserDetail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TutorSettingController {

    private ITSetting service = new TSettingService();
    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);

    @PostMapping(path = "/setting")
    @ResponseBody
    public TSettingResponse tsetting(@RequestBody ChangeEmailForm form){
        try{
            TSettingResponse response = service.changeemail(form);
            return response;
        } catch(Exception e){
            logger.error("ERROR",e);
            TSettingResponse response = new TSettingResponse();
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
            return response;
        }
    }

}
