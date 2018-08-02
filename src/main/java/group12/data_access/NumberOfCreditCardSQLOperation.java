package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NumberOfCreditCardSQLOperation extends SQLOperationTemplate {
    public NumberOfCreditCardSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "SELECT NumberOfCreditCard(?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String cardNumber = (String) getParameters().get(0);
        ps.setString(1, cardNumber);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        int numberOfCards;
        numberOfCards = rs.getInt(1);
        return numberOfCards;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
