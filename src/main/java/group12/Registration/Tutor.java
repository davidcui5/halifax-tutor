package group12;

public class Tutor extends User{

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String creditCardNumber;
    private String expireDate;
    private int securityCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
