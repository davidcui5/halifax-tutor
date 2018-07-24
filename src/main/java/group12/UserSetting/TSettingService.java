package group12.UserSetting;

import group12.data_access.MysqlDAOImpl;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TSettingService implements ITSetting{

    private static final Logger logger = LogManager.getLogger(TSettingService.class);
    private MysqlDAOImpl db;

    @Override
    public TSettingResponse changeEmail(ChangeEmailForm form, String oldemail) {
        TSettingResponse response = new TSettingResponse();
        String newemail = form.getEmail();
        if (form == null || oldemail==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid Email Form");
            logger.info(response);
            return response;
        }

        try{
            //if the email is new
            if (db.countOfUserWithEmail(newemail)<=0){

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
        IEncryptor encrypt = new SimpleMD5Encryptor();
        String newpassword = form.getPassword();

        if (form == null || useremail==null){
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

        if (form==null || useremail==null){
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

    @Override
    public TSettingResponse changeCard(ChangeCardForm form, String useremail) {
        TSettingResponse response = new TSettingResponse();
        String creditCardNumber = form.getCreditCardNumber();
        String expireDate = form.getExpireDate();
        int securityCode = form.getSecurityCode();

        if (form ==null || useremail ==null){
            response.setResult("FAILURE");
            response.setDetail("Invalid Card Form");
            logger.info(response);
            return response;
        }
        try {
            //TODO DB Update card
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
