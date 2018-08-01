package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorExperienceSQLOperation extends SQLOperationTemplate {

    public UpdateTutorExperienceSQLOperation(String email, String experience){
        super(email, experience);
    }

    //TODO: function
    @Override
    protected String makeSQL() {
        return "SELECT UpdateTutorExperience(?, ?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String experience = (String) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, experience);
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