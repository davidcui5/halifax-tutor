package group12.data_access;

import group12.login.IAuthenticationStrategy;
import group12.login.IRedirectionStrategy;
import group12.login.TutorAuthStrategy;
import group12.login.TutorRedirectStrategy;

public class Tutor extends User {

    private int tutorID;
    private String firstName;
    private String lastName;
    private boolean activated;
    private boolean banned;
    private String phoneNumber;
    private String bio;
    private int planID;
    private String expiryDate;
    private String creditCardHolder;
    private String creditCardNum;
    private String creditCardExpiryDate;
    private int securityCode;

    public Tutor(){
        super();
    }

    public Tutor(String email, String password){
        super(email, password);
    }

    @Override
    public IAuthenticationStrategy createAuthenticationStrategy() {
        return new TutorAuthStrategy();
    }

    @Override
    public IRedirectionStrategy createRedirectionStrategy() {
        return new TutorRedirectStrategy();
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
