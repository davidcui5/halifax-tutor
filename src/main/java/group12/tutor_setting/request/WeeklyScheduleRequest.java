package group12.tutor_setting.request;

public class WeeklyScheduleRequest {
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
}
