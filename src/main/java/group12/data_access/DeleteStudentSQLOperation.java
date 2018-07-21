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
        return "SELECT DeleteStudent(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int id = (int) getParameters().get(0);
        ps.setInt(1, id);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int result = rs.getInt(1);
        return result;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
