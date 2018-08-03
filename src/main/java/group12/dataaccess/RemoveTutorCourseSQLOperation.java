package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveTutorCourseSQLOperation extends SQLDMLOperation {

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
    boolean isSuccess(int numOfResult) {
        return numOfResult == 1;
    }
}
