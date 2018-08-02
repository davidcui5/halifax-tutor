package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NumberOfEmailSQLOperation extends SQLOperationTemplate {

    public NumberOfEmailSQLOperation(Object... parameters) {
        super(parameters);
    }

    private static Logger logger = LogManager.getLogger(NumberOfEmailSQLOperation.class);

    @Override
    protected String makeSQL() {
        return "SELECT NumberOfEmail(?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1, email);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        int numberOfEmails;
        numberOfEmails = rs.getInt(1);
        return numberOfEmails;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        return resultSet;
    }
}
