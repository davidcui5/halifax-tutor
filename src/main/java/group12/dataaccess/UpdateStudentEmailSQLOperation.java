package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateStudentEmailSQLOperation extends SQLOperationTemplate {
    public UpdateStudentEmailSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "SELECT UpdateStudentEmail(?,?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String oldEmail = (String) getParameters().get(0);
        String newEmail = (String) getParameters().get(1);
        ps.setString(1, oldEmail);
        ps.setString(2, newEmail);
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