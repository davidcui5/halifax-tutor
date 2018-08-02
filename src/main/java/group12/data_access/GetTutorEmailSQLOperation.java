package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTutorEmailSQLOperation extends SQLOperationTemplate {
    public GetTutorEmailSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "CALL GETTutorEmail(?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1, email);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        TutorParser tutorParser = new TutorParser();
        Tutor tutor = tutorParser.parse(rs);
        return tutor;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
