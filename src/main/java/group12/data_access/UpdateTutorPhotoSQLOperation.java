package group12.data_access;

import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorPhotoSQLOperation extends SQLOperationTemplate {
    private static Logger logger = LogManager.getLogger(UpdateTutorPhotoSQLOperation.class);

    public UpdateTutorPhotoSQLOperation(String email, String photoURL) {
        super(email, photoURL);
    }

    @Override
    protected String makeSQL() {
        return "UPDATE Tutor " +
        "SET Tutor.PhotoUrl = ? " +
        "WHERE Tutor.Email = ?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        logger.log(Level.INFO, email);
        String photoURL = (String) getParameters().get(1);
        logger.log(Level.INFO, photoURL);
        ps.setString(1, photoURL);
        ps.setString(2, email);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return null;
    }

    protected int executeUpdateStatement(PreparedStatement ps) throws SQLException {
        int result = ps.executeUpdate();
        logger.log(Level.INFO, result);
        return result;
    }

    public Object executeMysqlUpdate() {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = makeSQL();
        int numResult = 0;
        boolean result = false;
        try {
            con = ConnectionFactory.getDatabaseConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
            numResult = executeUpdateStatement(ps);
            if (numResult >= 1) {
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
