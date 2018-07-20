package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegTutorSQLOperation extends SQLOperationTemplate {
    public RegTutorSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT RegTutor(?,?,?,?,?,?,?,?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String firstName = (String) getParameters().get(0);
        String lastName = (String) getParameters().get(1);
        String email = (String) getParameters().get(2);
        String password = (String) getParameters().get(3);
        String phoneNumber = (String) getParameters().get(4);
        String cardHolderName = (String) getParameters().get(5);
        String cardNumber = (String) getParameters().get(6);
        String expertDate = (String) getParameters().get(7);
        String securityCode = (String) getParameters().get(8);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setString(5, phoneNumber);
        ps.setString(6, cardHolderName);
        ps.setString(7, cardNumber);
        ps.setString(8, expertDate);
        ps.setString(9, securityCode);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
