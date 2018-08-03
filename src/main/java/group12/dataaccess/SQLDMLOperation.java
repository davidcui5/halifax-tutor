package group12.dataaccess;

import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLDMLOperation extends SQLOperationTemplate {
    private static final Logger logger = LogManager.getLogger(SQLDMLOperation.class);

    public SQLDMLOperation(Object... parameters) {
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

    abstract boolean isSuccess(int numOfResult);

    public boolean executeMysqlUpdate() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = makeSQL();
        int numOfResult;
        boolean isSuccess = false;
        try {
            con = ConnectionFactory.getDatabaseConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
            numOfResult = ps.executeUpdate();
            isSuccess = isSuccess(numOfResult);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return isSuccess;
    }
}
