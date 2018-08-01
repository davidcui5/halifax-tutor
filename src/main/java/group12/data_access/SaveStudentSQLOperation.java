package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveStudentSQLOperation extends SQLOperationTemplate {
    public SaveStudentSQLOperation(Student student) {
        super(student);
    }

    @Override
    protected String makeSQL() {
        return "SELECT SaveStudent(?,?,?,?,?,?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        Student s = (Student) getParameters().get(0);
        ps.setString(1, s.getFirstName());
        ps.setString(2, s.getLastName());
        ps.setString(3, s.getEmail());
        ps.setString(4, s.getPassword());
        ps.setString(5, s.getSchool());
        ps.setString(6, s.getPhoneNumber());
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