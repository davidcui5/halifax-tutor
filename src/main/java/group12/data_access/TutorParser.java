package group12.data_access;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorParser {
    public Tutor parse(ResultSet rs) throws SQLException {
        Tutor tutor = new Tutor();
        tutor.setEmail(rs.getString("Email"));
        tutor.setPassword(rs.getString("Password"));
        tutor.setTutorID(rs.getInt("ID"));
        tutor.setFirstName(rs.getString("FirstName"));
        tutor.setLastName(rs.getString("LastName"));
        tutor.setActivated(rs.getBoolean("AccountActivation"));
        tutor.setBanned(rs.getBoolean("Banned"));
        tutor.setPhoneNumber(rs.getString("PhoneNumber"));
        tutor.setEducation(rs.getString("Education"));
        tutor.setPlanID(rs.getInt("PlanID"));
        tutor.setExpiryDate(rs.getString("ExpiryDate"));
        tutor.setCreditCardHolder(rs.getString("CreditCardHoldName"));
        tutor.setCreditCardNum(rs.getString("CreditCardNumber"));
        tutor.setCreditCardExpiryDate(rs.getString("CreditCardExpiryDate"));
        tutor.setSecurityCode(rs.getString("SecurityCode"));
        tutor.setExperience(rs.getString("Experience"));
        tutor.setRating(rs.getFloat("Rating"));
        tutor.setTotalRating(rs.getInt("TotalRatings"));
        return tutor;
    }
}
