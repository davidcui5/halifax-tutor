package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTutorByIDSQLOp extends SQLOperationTemplate {
    public GetTutorByIDSQLOp(int tutorID){
        super(tutorID);
    }

    @Override
    String makeSQL() {
        return "SELECT * FROM Tutor WHERE ID = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int tutorID = (int)getParameters().get(0);
        ps.setInt(1,tutorID);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        TutorParser tutorParser = new TutorParser();
        Tutor tutor = tutorParser.parse(rs);
        return tutor;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
