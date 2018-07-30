package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSearchAuthConfSQlOperation extends SQLOperationTemplate {
    public GetSearchAuthConfSQlOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "select GetSearchAuthConf()";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
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
