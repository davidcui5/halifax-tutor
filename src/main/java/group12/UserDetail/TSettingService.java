package group12.UserDetail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TSettingService implements ITSetting{

    private static final Logger logger = LogManager.getLogger(TSettingService.class);

    @Override
    public TSettingResponse changeemail(ChangeEmailForm form) {
        TSettingResponse response = new TSettingResponse();
        if (form == null || form.getEmail()==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid ChangeEmailForm");
            logger.info(response);
            return response;
        }

        String email = form.getEmail();

        try{
            response = change(email);
        }catch(Exception e){
            logger.error("ERROR",e);
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
        }

    }

    private TSettingResponse change(String email){

    }
}
