package group12.UserDetail;

import group12.DBDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TSettingService implements ITSetting{

    private static final Logger logger = LogManager.getLogger(TSettingService.class);
    private DBDAO db;

    @Override
    public TSettingResponse changeemail(ChangeEmailForm form, String useremail) {
        TSettingResponse response = new TSettingResponse();
        if (form == null || form.getEmail()==null || useremail==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid Email Form");
            logger.info(response);
            return response;
        }

        String newemail = form.getEmail();

        try{
            response = changeEmail(newemail,useremail);
        }catch(Exception e){
            logger.error("ERROR",e);
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
        }

        logger.info(response);
        return response;
    }

    private TSettingResponse changeEmail(String newemail,String oldemail){
        TSettingResponse response = new TSettingResponse();
        //if the email is new
        if (db.isEmailNew(oldemail)){
            int uid = db.getTutorID(oldemail);

            //TODO Update email


            return response;

        }else{
            response.setResult("Failure");
            response.setDetail("Email already registered");
            return response;
        }
    }
}
