package group12.data_access;

import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SQLOperationTemplate {

    private ArrayList<Object> parameters;
    private static Logger logger = LogManager.getLogger(SQLOperationTemplate.class);
    private String sql;

    public SQLOperationTemplate(Object... parameters) {
        this.parameters = new ArrayList<Object>();
        for (Object parameter : parameters) {
            this.parameters.add(parameter);
        }
    }

    protected abstract String makeSQL();

    protected abstract PreparedStatement addParameters(PreparedStatement ps) throws SQLException;

    protected abstract Object extractResultSet(ResultSet rs) throws SQLException;

    protected abstract ResultSet execute(PreparedStatement ps) throws SQLException;

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public Object executeMysqlQuery() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        sql = makeSQL();
        Object result = null;
        try {
            con = ConnectionFactory.getDatabaseConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
            rs = execute(ps);
            if (rs.next()) {
                result = extractResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error("SQL Error " + sql, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
