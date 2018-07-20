package group12.registration;

public class TutorSignupForm extends UserSignupForm {

    private String creditCardHoldName;
    private String creditCardNumber;
    private String expireDate;
    private String securityCode;

    public String getCreditCardHoldName() {
        return creditCardHoldName;
    }

    public void setCreditCardHoldName(String creditCardHoldName) {
        this.creditCardHoldName = creditCardHoldName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return super.toString() + "Card: " + creditCardNumber + "Expiry:" + expireDate + "SecCode: " + securityCode;
    }
}
