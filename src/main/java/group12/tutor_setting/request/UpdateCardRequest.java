package group12.tutor_setting.request;

public class UpdateCardRequest {
    private String token;
    private String holderName;
    private String creditCardNumber;
    private String expireDate;
    private int securityCode;

    public void setToken(String token) {
        this.token = token;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getToken() {
        return token;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }
}
