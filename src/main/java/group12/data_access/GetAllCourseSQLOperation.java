package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAllCourseSQLOperation extends SQLOperationTemplate {
    public GetAllCourseSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "Call GetAllCourses()";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        List<Course> courses = new ArrayList<>();
        CourseParserWithoutPrice courseParser = new CourseParserWithoutPrice();
        courses.add(courseParser.parse(rs));
        while (rs.next()) {
            courses.add(courseParser.parse(rs));
        }
        return courses;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
