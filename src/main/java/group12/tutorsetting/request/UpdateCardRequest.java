package group12.tutorsetting.request;

public class UpdateCardRequest extends TutorSettingRequest {
    private String holderName;
    private String creditCardNumber;
    private String expireDate;
    private int securityCode;

    public UpdateCardRequest() {

    }

    public UpdateCardRequest(String token, String holderName, String creditCardNumber, String expireDate,
                             int securityCode) {
        super(token);
        this.holderName = holderName;
        this.creditCardNumber = creditCardNumber;
        this.expireDate = expireDate;
        this.securityCode = securityCode;
    }

    public void setToken(String token) {
        super.setToken(token);
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
        return super.getToken();
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

    @Override
    public String toString() {
        return super.toString() + " holderName: " + holderName + " creditCardNumber: " + creditCardNumber
                + " expireDate: " + expireDate + " securityCode: " + securityCode;
    }
}
