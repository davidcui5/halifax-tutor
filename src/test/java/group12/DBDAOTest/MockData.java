package group12.DBDAOTest;

import group12.Registration.Student;
import group12.Registration.Tutor;

public class MockData {
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
        return tutor;
    }
}
