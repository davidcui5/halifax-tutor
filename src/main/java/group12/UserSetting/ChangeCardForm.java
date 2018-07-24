package group12.UserSetting;

public class ChangeCardForm {
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
        if( creditCardNumber != null && expireDate!=null && securityCode!=0 ){
            return " CreditCardNumber: " + creditCardNumber+"ExpireDate"+expireDate+"SecurityCode"+securityCode;
        }
        else{
            return "Invalid Changing Card info Form: Contains NULL";
        }
    }
}
