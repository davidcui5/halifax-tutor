package group12.UserSetting;

import group12.DBDAO;
import group12.Registration.IEncrypt;
import group12.Registration.SimpleMD5Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TSettingService implements ITSetting{

    private static final Logger logger = LogManager.getLogger(TSettingService.class);
    private DBDAO db;

    @Override
    public TSettingResponse changeEmail(ChangeEmailForm form, String oldemail) {
        TSettingResponse response = new TSettingResponse();
        String newemail = form.getEmail();
        if (form == null || newemail==null || oldemail==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid Email Form");
            logger.info(response);
            return response;
        }

        try{
            //if the email is new
            if (db.isEmailNew(newemail)){

                //TODO DB Update email

                return response;

            }else{
                response.setResult("Failure");
                response.setDetail("Email already registered");
                return response;
            }

        }catch(Exception e){
            logger.error("ERROR",e);
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
        }

        logger.info(response);
        return response;
    }

    @Override
    public TSettingResponse changePwd(ChangePwdForm form, String useremail) {
        TSettingResponse response = new TSettingResponse();
        IEncrypt encrypt = new SimpleMD5Encryptor();
        String newpassword = form.getPassword();

        if (form == null || newpassword==null || useremail==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid Password Form");
            logger.info(response);
            return response;
        }
        try{


            //TODO DB Update password

            return response;

        }catch(Exception e){
            logger.error("ERROR",e);
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
        }

        logger.info(response);
        return response;
    }

    @Override
    public TSettingResponse changePhone(ChangePhoneForm form, String useremail) {
        TSettingResponse response = new TSettingResponse();
        String newPhone = form.getPhone();

        if (form==null || newPhone==null || useremail==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid Phone Form");
            logger.info(response);
            return response;
        }
        try{

            //TODO DB Update phone

            return response;

        }catch (Exception e){

            logger.error("ERROR",e);
            response.setResult("FAILURE");
            response.setDetail("Server Error, Please Return Later or Contact Admin");
        }
        logger.info(response);
        return response;
    }
}
