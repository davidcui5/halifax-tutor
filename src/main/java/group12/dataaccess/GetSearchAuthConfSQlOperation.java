package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSearchAuthConfSQlOperation extends SQLOperationTemplate {
    public GetSearchAuthConfSQlOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "select GetSearchAuthConf()";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
