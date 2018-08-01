package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetCourseToTutorSQLOperation extends SQLOperationTemplate {
    public SetCourseToTutorSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "SELECT SetCourseToTutor(?,?,?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int tutorId = (int) getParameters().get(0);
        int courseId = (int) getParameters().get(1);
        float price = (float) getParameters().get(2);
        ps.setInt(1, tutorId);
        ps.setInt(2, courseId);
        ps.setFloat(3, price);
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
