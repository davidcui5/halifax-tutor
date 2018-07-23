package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteReviewByIDSQLOperation extends SQLOperationTemplate{
    public DeleteReviewByIDSQLOperation(int id){
        super(id);
    }

    @Override
    String makeSQL() {
        return "SELECT DeleteReview(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int id = (int)getParameters().get(0);
        ps.setInt(1,id);
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