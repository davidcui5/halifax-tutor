package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCourseByNameSQLOperation extends SQLOperationTemplate {
    public GetCourseByNameSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "CALL GetCourseByName(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String courseName = (String) getParameters().get(0);
        ps.setString(1, courseName);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        CourseParserWithoutPrice courseParser=new CourseParserWithoutPrice();
        Course course = courseParser.parse(rs);
        return course;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
