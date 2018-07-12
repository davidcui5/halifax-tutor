package group12.data_access;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetStudentDatabaseOperation extends DatabaseOperationTemplate {

    @Override
    String makeSQL() {
        return "SELECT * FROM Student Where Email =?";
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setPassword(rs.getString("Password"));
        student.setActivated(rs.getBoolean("AccountActivation"));
        student.setBanned(rs.getBoolean("Banned"));
        return student;
    }

}
