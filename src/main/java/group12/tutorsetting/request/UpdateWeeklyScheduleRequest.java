package group12.tutorsetting.request;

import java.util.Arrays;

public class UpdateWeeklyScheduleRequest {
    private String token;
    private boolean[][] weeklySchedule;

    public void setToken(String token) {
        this.token = token;
    }

    public void setWeeklySchedule(boolean[][] weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public String getToken() {
        return token;
    }

    public boolean[][] getWeeklySchedule() {
        return weeklySchedule;
    }

    @Override
    public String toString() {
        String result = "";
        result = result.concat("token: ".concat(token));
        for (boolean[] aWeeklySchedule : weeklySchedule) {
            result = result.concat(Arrays.toString(aWeeklySchedule));
        }
        return result;
    }
}
