package group12.tutorsetting;

import group12.dataaccess.tutorsetting.WeeklySchedule;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.request.UpdatePasswordRequest;
import group12.tutorsetting.request.UpdateWeeklyScheduleRequest;

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
}
