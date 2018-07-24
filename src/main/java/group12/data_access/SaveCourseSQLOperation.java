package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveCourseSQLOperation extends SQLOperationTemplate {
    public SaveCourseSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT SaveCourse(?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String courseName = (String) getParameters().get(0);
        String courseSchool = (String) getParameters().get(1);
        ps.setString(1, courseName);
        ps.setString(2, courseSchool);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int result = rs.getInt(1);
        return result;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
