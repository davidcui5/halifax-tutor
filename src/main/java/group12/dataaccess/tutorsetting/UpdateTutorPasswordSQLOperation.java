package group12.dataaccess.tutorsetting;

import group12.dataaccess.SQLOperationTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorPasswordSQLOperation extends SQLOperationTemplate {
    public UpdateTutorPasswordSQLOperation(String email, String password){
        super(email, password);
    }

    @Override
    protected String makeSQL() {
        return "SELECT UpdateTutorPassword(?, ?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String password = (String) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, password);
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