package group12.tutorsetting;

import group12.dataaccess.tutorsetting.WeeklySchedule;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.registration.RegistrationService;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.request.*;

class TutorSettingService {
    private ITutorSettingDAO tutorSettingDAO = new TutorSettingDAOImpl();
    private IAccessToken accessToken = JWTAccessToken.getInstance();

    TutorSettingResponse getUpdateWeeklyScheduleResponse(UpdateWeeklyScheduleRequest updateWeeklyScheduleRequest) {
        String token = updateWeeklyScheduleRequest.getToken();
        String email = accessToken.decodeToken(token);

        boolean[][] schedule = updateWeeklyScheduleRequest.getWeeklySchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule(schedule);

        boolean success = tutorSettingDAO.updateWeeklySchedule(email, weeklySchedule);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePasswordResponse(UpdatePasswordRequest updatePasswordRequest) {
        String token = updatePasswordRequest.getToken();
        String password = updatePasswordRequest.getPassword();
        IEncryptor encryptor = SimpleMD5Encryptor.getInstance();

        password = encryptor.encrypt(password);

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorPassword(email, password);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateEmailResponse(UpdateEmailRequest updateEmailRequest) {
        String token = updateEmailRequest.getToken();

        String email = accessToken.decodeToken(token);

        String newEmail = updateEmailRequest.getEmail();

        boolean success = tutorSettingDAO.updateTutorEmail(email, newEmail);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdatePhoneResponse(UpdatePhoneRequest updatePhoneRequest) {
        String token = updatePhoneRequest.getToken();
        String phone = updatePhoneRequest.getPhone();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorPhone(email, phone);
        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getUpdateCardResponse(UpdateCardRequest updateCardRequest) {
        String token = updateCardRequest.getToken();
        String holderName = updateCardRequest.getHolderName();
        String creditCardNumber = updateCardRequest.getCreditCardNumber();
        String expiryDate = updateCardRequest.getExpireDate();
        int securityCode = updateCardRequest.getSecurityCode();

        String email = accessToken.decodeToken(token);

        boolean success = tutorSettingDAO.updateTutorCard(email, holderName, creditCardNumber, expiryDate, securityCode);

        return new TutorSettingResponse(success);
    }

    TutorSettingResponse getResendConfirmationEmailResponse(ResendConfirmationRequest resendConfirmationRequest) {
        String token = resendConfirmationRequest.getToken();

        String email = accessToken.decodeToken(token);

        new RegistrationService().sendTutorActivationEmail(email);

        return new TutorSettingResponse(true);
    }
}
