package group12.data_access.tutor_setting;

public class WeeklySchedule {

    private int numOfDays;

    private int numOfTime;

    private boolean[][] weeklySchedule;

    public WeeklySchedule() {
        numOfDays = 7;
        numOfTime = 3;
        this.weeklySchedule = new boolean[numOfDays][numOfTime];
    }

    public WeeklySchedule(int numOfDays, int numOfTime) {
        this.numOfDays = numOfDays;
        this.numOfTime = numOfTime;
        weeklySchedule = new boolean[numOfDays][numOfTime];
    }

    public WeeklySchedule(boolean[][] weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
        numOfDays = weeklySchedule.length;
        numOfTime = weeklySchedule[0].length;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public int getNumOfTime() {
        return numOfTime;
    }

    public void setOneSlot(int day, int time, boolean availability) {
        this.weeklySchedule[day][time] = availability;
    }

    public boolean getOneSlot(int day, int time) {
        return weeklySchedule[day][time];
    }

    public void setAllSlots(boolean[][] weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
        numOfDays = weeklySchedule.length;
        numOfTime = weeklySchedule[0].length;
    }
}
