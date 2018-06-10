package group12;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setFirstName(resultSet.getString("FirstName"));
        student.setLastName(resultSet.getString("LastName"));
        student.setSchool(resultSet.getString("School"));
        student.setEmail(resultSet.getString("Email"));
        return null;
    }
}
