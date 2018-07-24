package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetTutorActivatedStatusSQLOperation extends SQLOperationTemplate {
    public SetTutorActivatedStatusSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT SetTutorActivatedStatus(?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int id = (int) getParameters().get(0);
        boolean activationStatus = (boolean) getParameters().get(1);
        ps.setInt(1, id);
        ps.setBoolean(2, activationStatus);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
