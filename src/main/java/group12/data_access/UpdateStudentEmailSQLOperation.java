package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateStudentEmailSQLOperation extends SQLOperationTemplate {
    public UpdateStudentEmailSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT UpdateStudentEmail(?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String oldEmail = (String) getParameters().get(0);
        String newEmail = (String) getParameters().get(1);
        ps.setString(1, oldEmail);
        ps.setString(2, newEmail);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}