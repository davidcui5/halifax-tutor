package group12.data_access;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseParserWithPrice {
    public Course parse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("ID"));
        course.setName(rs.getString("Name"));
        course.setSchool(rs.getString("School"));
        course.setPrice(rs.getFloat("Price"));
        return course;
    }
}
