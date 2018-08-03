package group12.dataaccess;

import group12.search.dataaccess.TutorPublicInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTutorPublicInfoSQLOperation extends SQLOperationTemplate {

    public GetTutorPublicInfoSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT Tutor.Banned, Tutor.ID, Tutor.PhotoUrl, Tutor.FirstName, Tutor.LastName, Tutor.Education, " +
                "Tutor.Rating, TutorCourse.Price " +
                "FROM TutorCourse " +
                "JOIN Tutor on TutorCourse.TutorId = Tutor.ID " +
                "JOIN Course on TutorCourse.CourseId = Course.ID " +
                "WHERE " +
                "Course.School = ? AND Course.Name = ?";
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
        List<TutorPublicInfo> results = new ArrayList<>();
        do {
            int id = rs.getInt("ID");
            String photoURL = rs.getString("PhotoURL");
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String education = rs.getString("Education");
            float rating = rs.getFloat("Rating");
            float price = rs.getFloat("Price");
            boolean banned = rs.getBoolean("Banned");
            results.add(new TutorPublicInfo(id, photoURL, firstName, lastName, education, rating, price, banned));
        } while (rs.next());
        return results;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
