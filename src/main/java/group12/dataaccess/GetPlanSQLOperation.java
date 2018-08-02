package group12.dataaccess;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetPlanSQLOperation extends SQLOperationTemplate{
    private static Logger logger = LogManager.getLogger(GetPlanSQLOperation.class);

//    private int ID;
    public GetPlanSQLOperation(int ID) {
//        this.ID = ID;
    }


    @Override
    protected String makeSQL() {
        return "SELECE * FROM SubscriptionPlan WHERE ID=?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int ID = (int) getParameters().get(0);
        ps.setInt(1,ID);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        Subscribe_Plan plan = new Subscribe_Plan();
        plan.setID(rs.getInt("ID"));
        plan.setName(rs.getString("Name"));
        plan.setDescription(rs.getString("Description"));
        plan.setPrice(rs.getFloat("Price"));
        plan.setMonth(rs.getInt("Month"));
        return plan;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}

