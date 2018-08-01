package group12.tutorsetting;

import group12.dataaccess.tutorsetting.WeeklySchedule;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;
import group12.tutorsetting.request.WeeklyScheduleRequest;

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
