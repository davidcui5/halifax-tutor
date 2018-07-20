package group12.DBDAOTest;

import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;

public class MockData {
    public static StudentSignupForm getStudentObject() {
        StudentSignupForm student = new StudentSignupForm();
        student.setEmail("testemailStu@gmail.com");
        student.setFirstName("testName");
        student.setLastName("testLName");
        student.setSchool("testSchool");
        student.setPhoneNumber("9021234567S");
        student.setPassword("pass123456S");
        return student;
    }

    public static TutorSignupForm getTutorObject() {
        TutorSignupForm tutor = new TutorSignupForm();
        tutor.setEmail("testemailTut@gmail.com");
        tutor.setFirstName("testName");
        tutor.setLastName("testLName");
        tutor.setPhoneNumber("9021234567T");
        tutor.setPassword("pass123456T");
        tutor.setCreditCardHoldName(tutor.getFirstName());
        tutor.setCreditCardNumber("1234567891234567");
        tutor.setExpireDate("12-12");
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

    public static StudentSignupForm getUnRegStudent() {
        StudentSignupForm student = new StudentSignupForm();
        student.setEmail("testemailWrong@gmail.com");
        student.setPassword("pass123456S");
        return student;
    }

    public static TutorSignupForm getUnRegTutor() {
        TutorSignupForm tutor = new TutorSignupForm();
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
        return "e9a577f4-827d-45ee-bde0-3ec0d49d3298";
    }

    public static String getWrongActivationCode() {
        return "e9a577f4-827d-47Ee-bde0-3ec0d49d3298";
    }

    public static String getOldActivationCode() {
        return "713ec00e-e9ae-4833-bba2-75d8729f554e";
    }
}

