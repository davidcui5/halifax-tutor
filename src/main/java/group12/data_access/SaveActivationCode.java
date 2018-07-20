package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveActivationCode extends SQLOperationTemplate {
    public SaveActivationCode(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "Select SaveActivationCode(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String activeCode = (String) getParameters().get(0);
        ps.setString(1, activeCode);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
