package group12.tutorsetting;

import group12.dataaccess.tutorsetting.WeeklySchedule;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.request.UpdateWeeklyScheduleRequest;

class TutorSettingService {
    private ITutorSettingDAO tutorSettingDAO = new TutorSettingDAOImpl();
    private IAccessToken accessToken = JWTAccessToken.getInstance();

    TutorSettingResponse getChangeWeeklyScheduleResponse(UpdateWeeklyScheduleRequest updateWeeklyScheduleRequest) {
        String token = updateWeeklyScheduleRequest.getToken();
        String email = accessToken.decodeToken(token);

        boolean[][] schedule = updateWeeklyScheduleRequest.getWeeklySchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule(schedule);

        boolean success = tutorSettingDAO.setWeeklySchedule(email, weeklySchedule);

        return new TutorSettingResponse(success);
    }
}
