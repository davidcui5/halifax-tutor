package group12.tutorprofile;

import java.util.ArrayList;

public class TutorProfileForm {

    private String emailToken;
    private int id;
    private String photoURL;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String education;
    private String rating;
    private String feedback;
    private ArrayList<String[]> courseList  ;
    private int[] tutorSchedule = new int[21];
    private String experience;
    private String message;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getRating() { return rating;    }

    public void setRating(String rating) { this.rating = rating; }

    public ArrayList<String[]> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<String[]> courseList) {
        this.courseList = courseList;
    }

    public int[] getTutorSchedule() {
        return tutorSchedule;
    }

    public void setTutorSchedule(int[] tutorSchedule) {
        this.tutorSchedule = tutorSchedule;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}

