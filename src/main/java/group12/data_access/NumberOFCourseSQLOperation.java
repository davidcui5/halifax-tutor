package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NumberOFCourseSQLOperation extends SQLOperationTemplate {

    public NumberOFCourseSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT NumberOfCourse(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String courseName = (String) getParameters().get(0);
        ps.setString(1, courseName);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
