package group12.data_access.tutor_setting;

import group12.data_access.SQLOperationTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorEducationSQLOperation extends SQLOperationTemplate {
    public UpdateTutorEducationSQLOperation(String email, String education){
        super(email, education);
    }

    //TODO: function
    @Override
    protected String makeSQL() {
        return "SELECT UpdateTutorEducation(?, ?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String education = (String) getParameters().get(1);
        ps.setString(1, email);
        ps.setString(2, education);
        return ps;
    }


    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);


    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();    }
}