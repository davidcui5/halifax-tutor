package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTutorWeeklyScheduleSQLOperation extends SQLDMLOperation {

    public UpdateTutorWeeklyScheduleSQLOperation(Integer tutorId, WeeklySchedule weeklySchedule) {
        super(tutorId, weeklySchedule);
    }

    @Override
    protected String makeSQL() {
        return "REPLACE INTO WeeklySchedule " +
                "SET Su1 = ?, Su2 = ?, Su3 = ?, Mo1 = ?, Mo2 = ?, Mo3 = ?, " +
                "Tu1 = ?, Tu2 = ?, Tu3 = ?, We1 = ?, We2 = ?, We3 = ?, " +
                "Th1 = ?, Th2 = ?, Th3 = ?, Fr1 = ?, Fr2 = ?, Fr3 = ?, " +
                "Sa1 = ?, Sa2 = ?, Sa3 = ?, " +
                "TutorID = ?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        Integer tutorId = (Integer) getParameters().get(0);
        WeeklySchedule weeklySchedule = (WeeklySchedule) getParameters().get(1);
        int index = 1;
        for (int i = 0; i < weeklySchedule.getNumOfDays(); i++) {
            for (int j = 0; j < weeklySchedule.getNumOfTime(); j++) {
                ps.setBoolean(index, weeklySchedule.getOneSlot(i, j));
                index++;
            }
        }
        ps.setInt(index, tutorId);
        return ps;
    }

    @Override
    boolean isSuccess(int numOfResult) {
        return numOfResult >= 1;
    }
}