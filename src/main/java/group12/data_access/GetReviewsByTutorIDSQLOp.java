package group12.data_access;

import group12.admin_setting.ReviewDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetReviewsByTutorIDSQLOp extends SQLOperationTemplate {
    public GetReviewsByTutorIDSQLOp(int tutorID){
        super(tutorID);
    }

    @Override
    String makeSQL() {
        return "SELECT * FROM Review WHERE TutorID = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int tutorID = (int)getParameters().get(0);
        ps.setInt(1,tutorID);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        List<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
        do{
            int reviewID = rs.getInt("ID");
            String text = rs.getString("Text");
            reviews.add(new ReviewDTO(reviewID, text));
        } while(rs.next());
        return reviews;
    }
}
