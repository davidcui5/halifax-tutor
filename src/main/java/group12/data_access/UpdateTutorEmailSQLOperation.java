package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorEmailSQLOperation extends SQLOperationTemplate {
    public UpdateTutorEmailSQLOperation(String email, String newemail){
        super(email, newemail);
    }

    @Override
    String makeSQL() {
        return "SELECT UpdateTutorEmail(?, ?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String newemail = (String) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, newemail);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}