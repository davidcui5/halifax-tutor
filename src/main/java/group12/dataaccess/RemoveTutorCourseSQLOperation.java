package group12.dataaccess;

import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveTutorCourseSQLOperation extends SQLOperationTemplate {
    private static Logger logger = LogManager.getLogger(RemoveTutorCourseSQLOperation.class);

    public RemoveTutorCourseSQLOperation(int tutorId, int courseId) {
        super(tutorId, courseId);
    }

    @Override
    String makeSQL() {
        return "DELETE FROM TutorCourse " +
                "WHERE TutorId = ? AND CourseId = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int tutorId = (int) getParameters().get(0);
        int courseId = (int) getParameters().get(1);
        ps.setInt(1, tutorId);
        ps.setInt(2, courseId);
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

    public boolean executeMysqlUpdate() {
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;
        String sql = makeSQL();
        boolean result = false;
        try {
            con = ConnectionFactory.getDatabaseConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
            rs = ps.executeUpdate();
            if (rs == 1) {
                result = true;
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
