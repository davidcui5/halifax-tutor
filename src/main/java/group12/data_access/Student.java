package group12.data_access;

import group12.login.IAuthenticationStrategy;
import group12.login.StudentAuthStrategy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends User {

    private int studentID;
    private String firstName;
    private String lastName;
    private boolean activated;
    private boolean banned;
    private String phoneNumber;
    private String school;

    public Student() {
        super();
    }

    public Student(String email, String password) {
        super(email, password);
    }

    @Override
    public IAuthenticationStrategy createAuthenticationStrategy() {
        return new StudentAuthStrategy();
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static Student studentParser(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setEmail(rs.getString("Email"));
        student.setPassword(rs.getString("Password"));
        student.setStudentID(rs.getInt("ID"));
        student.setFirstName(rs.getString("FirstName"));
        student.setLastName(rs.getString("LastName"));
        student.setActivated(rs.getBoolean("AccountActivation"));
        student.setBanned(rs.getBoolean("Banned"));
        student.setPhoneNumber(rs.getString("PhoneNumber"));
        return student;
    }
}
