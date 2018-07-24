package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetCoursesOfTutorSQLOperation extends SQLOperationTemplate {
    public GetCoursesOfTutorSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "CALL GetCoursesOFTutor(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int tutorId = (int) getParameters().get(0);
        ps.setInt(1, tutorId);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        List<Course> courses = new ArrayList<>();
        CourseParserWithPrice courseParser = new CourseParserWithPrice();
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
