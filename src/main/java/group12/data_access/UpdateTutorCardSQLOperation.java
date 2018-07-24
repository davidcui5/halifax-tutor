package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTutorCardSQLOperation extends SQLOperationTemplate {

    public UpdateTutorCardSQLOperation(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {

            super(email, creditCardHolder,creditCardNum,creditCardExpiryDate,securityCode);
    }

    @Override
    String makeSQL() {
        return "SELECT UpdateTutorCard(?, ?, ?, ?, ?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        String creditCardHolder = (String) getParameters().get(1);
        String creditCardNum = (String) getParameters().get(2);
        String creditCardExpiryDate = (String) getParameters().get(3);
        int securityCode = (Integer)(getParameters().get(4));

        ps.setString(1, email);
        ps.setString(2, creditCardHolder);
        ps.setString(3, creditCardNum);
        ps.setString(4, creditCardExpiryDate);
        ps.setInt(5, securityCode);
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