package group12.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseParserWithoutPrice {
    public Course parse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("ID"));
        course.setName(rs.getString("Name"));
        course.setSchool(rs.getString("School"));
        course.setPrice(0);
        return course;
    }
}
