package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckActivationCodeSQLOperation extends SQLOperationTemplate {
    public CheckActivationCodeSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "CALL CheckActivationCode(?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String code = (String) getParameters().get(0);
        ps.setString(1, code);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        ActivationCodeParser activationCodeParser=new ActivationCodeParser();
        ActivationCode activationCode = activationCodeParser.parse(rs);
        return activationCode;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
