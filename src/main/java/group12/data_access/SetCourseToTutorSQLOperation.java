package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetCourseToTutorSQLOperation extends SQLOperationTemplate {
    public SetCourseToTutorSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT SetCourseToTutor(?,?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int tutorId = (int) getParameters().get(0);
        int courseId = (int) getParameters().get(1);
        float price = (float) getParameters().get(2);
        ps.setInt(1, tutorId);
        ps.setInt(2, courseId);
        ps.setFloat(3, price);
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
