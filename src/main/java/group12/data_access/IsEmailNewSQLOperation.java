package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsEmailNewSQLOperation extends SQLOperationTemplate {

    public IsEmailNewSQLOperation(Object... parameters) {
        super(parameters);
    }

    private static Logger logger = LogManager.getLogger(IsEmailNewSQLOperation.class);

    @Override
    String makeSQL() {
        return "SELECT IsEmailNew(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1, email);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int numberOfEmails;
        numberOfEmails = rs.getInt(1);
        return numberOfEmails;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        return resultSet;
    }
}
