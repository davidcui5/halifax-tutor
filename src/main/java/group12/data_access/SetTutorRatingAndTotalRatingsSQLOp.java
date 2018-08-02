package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetTutorRatingAndTotalRatingsSQLOp extends SQLOperationTemplate {
    public SetTutorRatingAndTotalRatingsSQLOp(float rating, int totalRatings, int tutorID){
        super(rating, totalRatings, tutorID);
    }

    @Override
    String makeSQL() {
        return "SELECT SetTutorRatingAndTotalRatings(?, ?, ?);";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        float rating = (float)getParameters().get(0);
        ps.setFloat(1,rating);
        int totalRatings = (int)getParameters().get(1);
        ps.setInt(2,totalRatings);
        int tutorID = (int)getParameters().get(2);
        ps.setInt(3,tutorID);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
