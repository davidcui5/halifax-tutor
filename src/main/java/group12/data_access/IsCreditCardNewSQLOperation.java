package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsCreditCardNewSQLOperation extends SQLOperationTemplate {
    public IsCreditCardNewSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT IsCreditCardNew(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String cardNumber = (String) getParameters().get(0);
        ps.setString(1, cardNumber);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int numberOfCards;
        rs.next();
        numberOfCards = rs.getInt(1);
        return numberOfCards;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
