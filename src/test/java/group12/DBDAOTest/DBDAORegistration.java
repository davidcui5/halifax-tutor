package group12.DBDAOTest;

import group12.DBConnection.DBDAO;
import group12.Registration.Student;
import group12.Registration.Tutor;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.*;

public class DBDAORegistration {
    static ClassPathXmlApplicationContext context;
    static DBDAO dbda;

    @BeforeClass
    public static void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", DBDAO.class);
    }

    @Test
    public void testRegStudentRightInfo() {
        Student student = MockData.getStudentObject();
        boolean actual = dbda.regStudent(student);
        assertTrue(actual);
    }

    @Test
    public void testRegStudentReEmail() {
        Student student = MockData.getStudentObject();
        boolean actual = dbda.regStudent(student);
        assertFalse(actual);
    }

    @Test
    public void testRegStudentRePhone() {
        Student student = MockData.getStudentObject();
        student.setEmail("testemailStu2@gmail.com");
        boolean actual = dbda.regStudent(student);
        assertFalse(actual);
    }

    @Test
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        boolean actual = dbda.regTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testRegTutorReEmail() {
        Tutor tutor = MockData.getTutorObject();
        tutor.setEmail("testemailTut2@gmail.com");
        boolean actual = dbda.regTutor(tutor);
        assertFalse(actual);
    }

    @Test
    public void testSaveActivationCode() {
        boolean actual = dbda.saveActivationCode(MockData.getActivationCode());
        assertTrue(actual);
    }

    @AfterClass
    public static void testDeleteStudent() {
        Student student = MockData.getStudentObject();
        int id = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.delelteStudent(id);
        assertTrue(actual);
    }

    @AfterClass
    public static void testDeleteTutor() {
        Tutor tutor = MockData.getTutorObject();
        int id = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.delelteTutor(id);
        assertTrue(actual);
    }
}
