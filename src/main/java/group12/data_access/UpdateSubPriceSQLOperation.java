package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateSubPriceSQLOperation extends SQLOperationTemplate {
    public UpdateSubPriceSQLOperation(int planID, float price){
        super(planID, price);
    }

    @Override
    String makeSQL() {
        return "SELECT UpdateSubPlanPrice(?, ?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int planID = (int) getParameters().get(0);
        ps.setInt(1,planID);
        float price = (float) getParameters().get(1);
        ps.setFloat(2,price);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }
}
