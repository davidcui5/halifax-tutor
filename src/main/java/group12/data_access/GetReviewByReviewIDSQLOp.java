package group12.data_access;

import group12.admin_setting.ReviewDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetReviewByReviewIDSQLOp extends SQLOperationTemplate {
    public GetReviewByReviewIDSQLOp(int reviewID){
        super(reviewID);
    }

    @Override
    String makeSQL() {
        return "SELECT * FROM Review WHERE ID = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int reviewID = (int)getParameters().get(0);
        ps.setInt(1,reviewID);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int reviewID = rs.getInt("ID");
        String text = rs.getString("Text");
        float rating = rs.getFloat("Rate");
        int tutorID = rs.getInt("TutorID");
        return new ReviewDTO(reviewID, text, rating, tutorID);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
