package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteStudentSQLOperation extends SQLOperationTemplate {
    public DeleteStudentSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return null;
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        return null;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return null;
    }
}
