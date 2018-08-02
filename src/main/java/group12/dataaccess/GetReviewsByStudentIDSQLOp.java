package group12.dataaccess;

import group12.adminsetting.ReviewDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetReviewsByStudentIDSQLOp extends SQLOperationTemplate {
    public GetReviewsByStudentIDSQLOp(int studentID){
        super(studentID);
    }

    @Override
    protected String makeSQL() {
        return "SELECT * FROM Review WHERE StudentID = ?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int studentID = (int)getParameters().get(0);
        ps.setInt(1,studentID);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        List<ReviewDTO> reviews = new ArrayList<ReviewDTO>();
        do{
            int reviewID = rs.getInt("ID");
            String text = rs.getString("Text");
            reviews.add(new ReviewDTO(reviewID, text));
        } while(rs.next());
        return reviews;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
