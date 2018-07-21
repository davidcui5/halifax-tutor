package group12.data_access;

import group12.login.IAuthenticationStrategy;
import group12.login.TutorAuthStrategy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tutor extends User {

    private int tutorID;
    private String firstName;
    private String lastName;
    private boolean activated;
    private boolean banned;
    private String phoneNumber;
    private String education;
    private int planID;
    private String expiryDate;
    private String creditCardHolder;
    private String creditCardNum;
    private String creditCardExpiryDate;
    private String securityCode;

    public Tutor() {
        super();
    }

    public Tutor(String email, String password) {
        super(email, password);
    }

    @Override
    public IAuthenticationStrategy createAuthenticationStrategy() {
        return new TutorAuthStrategy();
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public String getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(String creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public static Tutor tutorParser(ResultSet rs) throws SQLException {
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
        return tutor;
    }
}
