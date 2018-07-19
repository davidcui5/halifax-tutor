package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizeStudent extends SQLOperationTemplate {
    public AuthorizeStudent(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "CALL AuthorizeStudent(?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String password = (String) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, password);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        Student student = Student.studentParser(rs);
        return student;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
