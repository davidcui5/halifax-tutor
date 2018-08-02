package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCourseByNameSQLOperation extends SQLOperationTemplate {
    public GetCourseByNameSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "CALL GetCourseByName(?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String courseName = (String) getParameters().get(0);
        ps.setString(1, courseName);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        CourseParserWithoutPrice courseParser=new CourseParserWithoutPrice();
        Course course = courseParser.parse(rs);
        return course;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
