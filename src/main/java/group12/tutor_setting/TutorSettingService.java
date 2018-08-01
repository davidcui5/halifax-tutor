package group12.tutor_setting;

import group12.data_access.tutor.WeeklySchedule;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import group12.tutor_setting.request.WeeklyScheduleRequest;

class TutorSettingService {
    private ITutorSettingDAO tutorSettingDAO = new TutorSettingDAOImpl();
    private IAccessToken accessToken = JWTAccessToken.getInstance();

    TutorSettingResponse getChangeWeeklyScheduleResponse(WeeklyScheduleRequest weeklyScheduleRequest) {
        String token = weeklyScheduleRequest.getToken();
        String email = accessToken.decodeToken(token);

        boolean[][] schedule = weeklyScheduleRequest.getWeeklySchedule();
        WeeklySchedule weeklySchedule = new WeeklySchedule(schedule);

        boolean success = tutorSettingDAO.setWeeklySchedule(email, weeklySchedule);

        return new TutorSettingResponse(success);
    }
}
