package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTutorSQLOperation extends SQLOperationTemplate{

    private static Logger logger = LogManager.getLogger(GetTutorSQLOperation.class);

    public GetTutorSQLOperation(Object... parameters){
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT * FROM Tutor Where Email =?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException{
        String email = (String) getParameters().get(0);
        ps.setString(1,email);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        Tutor tutor = new Tutor();
        tutor.setEmail(rs.getString("Email"));
        tutor.setPassword(rs.getString("Password"));
        tutor.setTutorID(rs.getInt("ID"));
        tutor.setFirstName(rs.getString("FirstName"));
        tutor.setLastName(rs.getString("LastName"));
        tutor.setActivated(rs.getBoolean("AccountActivation"));
        tutor.setBanned(rs.getBoolean("Banned"));
        tutor.setPhoneNumber(rs.getString("PhoneNumber"));
        tutor.setBio(rs.getString("Bio"));
        tutor.setPlanID(rs.getInt("PlanID"));
        tutor.setExpiryDate(rs.getString("SubExpiryDate"));
        tutor.setCreditCardHolder(rs.getString("CreditCardHoldName"));
        tutor.setCreditCardNum(rs.getString("CreditCardNumber"));
        tutor.setCreditCardExpiryDate(rs.getString("CreditCardExpiryDate"));
        tutor.setSecurityCode(rs.getInt("SecurityCode"));
        return tutor;
    }
}
