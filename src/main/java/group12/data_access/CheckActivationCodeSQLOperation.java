package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckActivationCodeSQLOperation extends SQLOperationTemplate {
    public CheckActivationCodeSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT CheckActivationCode(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String code = (String) getParameters().get(0);
        ps.setString(1, code);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        ActivationCode activationCode = ActivationCode.activationCodeParser(rs);
        return activationCode;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
