package group12.registration;

public class TutorSignupForm extends UserSignupForm {

    private String creditCardNumber;
    private String expireDate;
    private int securityCode;

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

    public int getSecurityCode() {
        return securityCode;
    }
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return super.toString() + "Card: " + creditCardNumber + "Expiry:" + expireDate + "SecCode: " + securityCode;
    }
}
