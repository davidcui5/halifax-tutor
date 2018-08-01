package group12.data_access.tutor;

import group12.data_access.SQLOperationTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorAvailabilitySQLOperation extends SQLOperationTemplate {
    public UpdateTutorAvailabilitySQLOperation(String email, WeeklySchedule weeklySchedule){
        super(email, weeklySchedule);
    }

    //TODO: function
    @Override
    protected String makeSQL() {
        return "SELECT UpdateTutorAvailability(?, ?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        WeeklySchedule weeklySchedule = (WeeklySchedule) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, weeklySchedule.getA1());
        ps.setString(3, weeklySchedule.getA2());
        ps.setString(4, weeklySchedule.getA3());
        ps.setString(5, weeklySchedule.getA4());
        ps.setString(6, weeklySchedule.getA5());
        ps.setString(7, weeklySchedule.getA6());
        ps.setString(8, weeklySchedule.getA7());
        ps.setString(9, weeklySchedule.getB1());
        ps.setString(10, weeklySchedule.getB2());
        ps.setString(11, weeklySchedule.getB3());
        ps.setString(12, weeklySchedule.getB4());
        ps.setString(13, weeklySchedule.getB5());
        ps.setString(14, weeklySchedule.getB6());
        ps.setString(15, weeklySchedule.getB7());
        ps.setString(16, weeklySchedule.getC1());
        ps.setString(17, weeklySchedule.getC2());
        ps.setString(18, weeklySchedule.getC3());
        ps.setString(19, weeklySchedule.getC4());
        ps.setString(20, weeklySchedule.getC5());
        ps.setString(21, weeklySchedule.getC6());
        ps.setString(22, weeklySchedule.getC7());
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}