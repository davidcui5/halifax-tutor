package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteReviewByIDSQLOperation extends SQLOperationTemplate{
    public DeleteReviewByIDSQLOperation(int id){
        super(id);
    }

    @Override
    protected String makeSQL() {
        return "SELECT DeleteReview(?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int id = (int)getParameters().get(0);
        ps.setInt(1,id);
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
