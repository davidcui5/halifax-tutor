package group12.data_access;

import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CancelTutorSubscriptionSQLOperation extends SQLOperationTemplate {
    private static Logger logger = LogManager.getLogger(CancelTutorSubscriptionSQLOperation.class);

    public CancelTutorSubscriptionSQLOperation(String email) {
        super(email);
    }

    @Override
    String makeSQL() {
        return "UPDATE Tutor " +
                "SET PlanID = null " +
                "WHERE Email = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1, email);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return null;
    }

    int executeUpdateStatement(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }

    public Object executeMysqlUpdate() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = makeSQL();
        int numResult = 0;
        boolean result = false;
        try {
            con = ConnectionFactory.getDatabaseConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
            numResult = executeUpdateStatement(ps);
            if (numResult == 1) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            logger.error("SQL Error " + sql, e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                logger.error("Close Connection Error", e);
            }
        }
        return result;
    }
}
