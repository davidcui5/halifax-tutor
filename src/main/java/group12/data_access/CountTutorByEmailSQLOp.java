package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountTutorByEmailSQLOp extends SQLOperationTemplate {
    public CountTutorByEmailSQLOp(String email){
        super(email);
    }

    @Override
    String makeSQL() {
        return "SELECT COUNT(*) FROM Tutor Where Email =?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1,email);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getInt(1);
    }
}
