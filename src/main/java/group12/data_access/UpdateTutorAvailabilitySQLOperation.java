package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorAvailabilitySQLOperation extends SQLOperationTemplate {
    public UpdateTutorAvailabilitySQLOperation(String email, Availability availability){
        super(email, availability);
    }

    //TODO: function
    @Override
    String makeSQL() {
        return "SELECT UpdateAvailabilityTutor(?, ?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        Availability availability = (Availability) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, availability.getA1());
        ps.setString(3, availability.getA2());
        ps.setString(4, availability.getA3());
        ps.setString(5, availability.getA4());
        ps.setString(6, availability.getA5());
        ps.setString(7, availability.getA6());
        ps.setString(8, availability.getA7());
        ps.setString(9, availability.getB1());
        ps.setString(10, availability.getB2());
        ps.setString(11, availability.getB3());
        ps.setString(12, availability.getB4());
        ps.setString(13, availability.getB5());
        ps.setString(14, availability.getB6());
        ps.setString(15, availability.getB7());
        ps.setString(16, availability.getC1());
        ps.setString(17, availability.getC2());
        ps.setString(18, availability.getC3());
        ps.setString(19, availability.getC4());
        ps.setString(20, availability.getC5());
        ps.setString(21, availability.getC6());
        ps.setString(22, availability.getC7());
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}