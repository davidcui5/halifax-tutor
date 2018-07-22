package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetTutorBannedStatusSQLOperation extends SQLOperationTemplate {
    public SetTutorBannedStatusSQLOperation(int id, boolean status) {
        super(id, status);
    }

    @Override
    String makeSQL() {
        return "SELECT SetTutorBannedStatus(?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int id = (int) getParameters().get(0);
        boolean status = (boolean) getParameters().get(1);
        ps.setInt(1, id);
        ps.setBoolean(2, status);
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
