package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorEducationSQLOperation extends SQLOperationTemplate {
    public UpdateTutorEducationSQLOperation(String email, String phone){
        super(email, phone);
    }

    @Override
    String makeSQL() {
        return "SELECT UpdateEducationTutor(?, ?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String education = (String) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, education);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }
}