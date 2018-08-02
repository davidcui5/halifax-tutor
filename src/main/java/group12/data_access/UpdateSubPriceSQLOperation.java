package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateSubPriceSQLOperation extends SQLOperationTemplate {
    public UpdateSubPriceSQLOperation(int planID, float price){
        super(planID, price);
    }

    @Override
    protected String makeSQL() {
        return "SELECT UpdateSubPlanPrice(?, ?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int planID = (int) getParameters().get(0);
        ps.setInt(1,planID);
        float price = (float) getParameters().get(1);
        ps.setFloat(2,price);
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
