package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CancelTutorSubscriptionSQLOperation extends SQLDMLOperation {
    public CancelTutorSubscriptionSQLOperation(String email) {
        super(email);
    }

    @Override
    String makeSQL() {
        return "UPDATE Tutor " +
                "SET PlanID = null " +
                "WHERE Email = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1, email);
        return ps;
    }

    @Override
    boolean isSuccess(int numOfResult) {
        return numOfResult == 1;
    }

}
