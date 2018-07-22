package group12.data_access;

import group12.logging.ConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetTutorPublicInfoSQLOperation extends SQLOperationTemplate {
    private static Logger logger = LogManager.getLogger(GetTutorPublicInfoSQLOperation.class);

    public GetTutorPublicInfoSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT Tutor.PhotoUrl, Tutor.FirstName, Tutor.LastName, Tutor.Education, " +
                " Tutor.Rating, TutorCourse.Price " +
                "FROM TutorCourse " +
                "JOIN Tutor on TutorCourse.TutorId = Tutor.ID " +
                "JOIN Course on TutorCourse.CourseId = Course.ID " +
                "WHERE " +
                " Course.School = ? AND Course.Name = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        ArrayList<Object> parameters = getParameters();
        String school = (String) parameters.get(0);
        String courseName = (String) parameters.get(1);
        ps.setString(1, school);
        ps.setString(2, courseName);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        String photoURL = rs.getString("PhotoURL");
        String firstName = rs.getString("FirstName");
        String lastName = rs.getString("LastName");
        String education = rs.getString("Education");
        float rating = rs.getFloat("Rating");
        float price = rs.getFloat("Price");
        return new TutorPublicInfo(photoURL, firstName, lastName, education, rating, price);
    }

    @Override
    public ArrayList<TutorPublicInfo> executeMysqlQuery() {
        ArrayList<TutorPublicInfo> results = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = makeSQL();
        try {
            con = ConnectionFactory.getDatabaseConnection();
            ps = con.prepareStatement(sql);
            ps = addParameters(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                TutorPublicInfo curInfo = (TutorPublicInfo) extractResultSet(rs);
                results.add(curInfo);
            }
        } catch (SQLException e) {
            logger.error("SQL Error", e);
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
        return results;
    }
}
