package group12.tutorsetting.request;

import java.util.Arrays;

public class UpdateWeeklyScheduleRequest extends TutorSettingRequest {
    private boolean[][] weeklySchedule;

    public UpdateWeeklyScheduleRequest() {

    }

    public UpdateWeeklyScheduleRequest(String token, boolean[][] weeklySchedule) {
        super(token);
        this.weeklySchedule = weeklySchedule;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setWeeklySchedule(boolean[][] weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public String getToken() {
        return super.getToken();
    }

    public boolean[][] getWeeklySchedule() {
        return weeklySchedule;
    }

    @Override
    public String toString() {
        String result = "";
        result = result.concat(super.toString());
        for (boolean[] aWeeklySchedule : weeklySchedule) {
            result = result.concat(Arrays.toString(aWeeklySchedule));
        }
        return result;
    }
}
