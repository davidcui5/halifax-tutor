package group12.DBDAOTest;

import group12.data_access.Course;
import group12.data_access.Student;
import group12.data_access.Tutor;

import java.util.UUID;

public class MockData {
    public static String activeCode = "";

    public static Student getStudentObject() {
        Student student = new Student();
        student.setEmail("testemailStu@gmail.com");
        student.setFirstName("testName");
        student.setLastName("testLName");
        student.setSchool("testSchool");
        student.setPhoneNumber("9021234567S");
        student.setPassword("pass123456S");
        return student;
    }

    public static Tutor getTutorObject() {
        Tutor tutor = new Tutor();
        tutor.setEmail("testemailTut@gmail.com");
        tutor.setFirstName("testName");
        tutor.setLastName("testLName");
        tutor.setPhoneNumber("9021234567T");
        tutor.setPassword("pass123456T");
        tutor.setCreditCardHolder(tutor.getFirstName());
        tutor.setCreditCardNum("123456789T234567");
        tutor.setExpiryDate("12-12");
        tutor.setSecurityCode("123");
        return tutor;
    }

    public static String getNewPhone() {
        return "9022212300";
    }

    public static String getUsedPhone() {
        return "9021234567T";
    }

    public static String getNewEmail() {
        return "testTesttest@TestTest.com";
    }

    public static String getUsedEmail() {
        return "testemailTut@gmail.com";
    }

    public static Student getUnRegStudent() {
        Student student = new Student();
        student.setEmail("testemailWrong@gmail.com");
        student.setPassword("pass123456S");
        return student;
    }

    public static Tutor getUnRegTutor() {
        Tutor tutor = new Tutor();
        tutor.setEmail("testemailWrong@gmail.com");
        tutor.setPassword("pass123456T");
        return tutor;
    }

    public static String getEmailRegistredStudent() {
        return "testemailStu@gmail.com";
    }

    public static String getEmailRegistredTutor() {
        return "testemailTut@gmail.com";
    }

    public static String getActivationCode() {
        UUID uuid = UUID.randomUUID();
        if (activeCode.isEmpty())
            activeCode = uuid.toString();
        return activeCode;
    }

    public static String getWrongActivationCode() {
        return activeCode;
    }

    public static String getOldActivationCode() {
        return "713ec00e-e9ae-4833-bba2-75d8729f554e";
    }

    public static Course getCourseObject() {
        Course course = new Course();
        course.setName("CSI101");
        course.setSchool("DAl");
        return course;
    }

    public static String getPasswordForTest() {
        return "testPassword";
    }

    public static String getPhoneForTest() {
        return "t9024412277";
    }

    public static String getEmailForTest() {
        return "testEmailtest@test.test";
    }
}

