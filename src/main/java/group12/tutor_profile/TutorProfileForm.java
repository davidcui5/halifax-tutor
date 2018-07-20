package group12.tutor_profile;

import java.util.ArrayList;

public class TutorProfileForm {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String education;
    private ArrayList<ArrayList<String>> coursePrice = new ArrayList<ArrayList<String>>();
    private String availability; // ToDo : what is availability format



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public ArrayList<ArrayList<String>> getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(ArrayList<ArrayList<String>> coursePrice) {
        this.coursePrice = coursePrice;
    }
}
