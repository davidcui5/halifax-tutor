package group12.data_access.tutor_setting;

import group12.data_access.SQLOperationTemplate;
import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorWeeklyScheduleSQLOperation extends SQLOperationTemplate {
    private static Logger logger = LogManager.getLogger(SQLOperationTemplate.class);

    public UpdateTutorWeeklyScheduleSQLOperation(Integer tutorId, WeeklySchedule weeklySchedule) {
        super(tutorId, weeklySchedule);
    }

    @Override
    protected String makeSQL() {
        return "REPLACE INTO WeeklySchedule " +
                "SET Su1 = ?, Su2 = ?, Su3 = ?, Mo1 = ?, Mo2 = ?, Mo3 = ?, " +
                "Tu1 = ?, Tu2 = ?, Tu3 = ?, We1 = ?, We2 = ?, We3 = ?, " +
                "Th1 = ?, Th2 = ?, Th3 = ?, Fr1 = ?, Fr2 = ?, Fr3 = ?, " +
                "Sa1 = ?, Sa2 = ?, Sa3 = ?, " +
                "TutorID = ?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        Integer tutorId = (Integer) getParameters().get(0);
        WeeklySchedule weeklySchedule = (WeeklySchedule) getParameters().get(1);
        int index = 1;
        for (int i = 0; i < weeklySchedule.getNumOfDays(); i++) {
            for (int j = 0; j < weeklySchedule.getNumOfTime(); j++) {
                ps.setBoolean(index, weeklySchedule.getOneSlot(i, j));
                index++;
            }
        }
        ps.setInt(index, tutorId);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
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
        ResultSet rs = null;
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