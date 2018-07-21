
package group12.DBDAOTest;

import group12.data_access.DBDAO;
import group12.data_access.Student;
import group12.data_access.Tutor;
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
        boolean actual = dbda.saveStudent(student);
        assertTrue(actual);
    }

    @Test
    public void testRegStudentReEmail() {
        Student student = MockData.getStudentObject();
        boolean actual = dbda.saveStudent(student);
        assertFalse(actual);
    }

    @Test
    public void testRegStudentRePhone() {
        Student student = MockData.getStudentObject();
        student.setEmail("testemailStu2@gmail.com");
        boolean actual = dbda.saveStudent(student);
        assertFalse(actual);
    }

    @Test
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        boolean actual = dbda.saveTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testRegTutorReEmail() {
        Tutor tutor = MockData.getTutorObject();
        tutor.setEmail("testemailTut2@gmail.com");
        boolean actual = dbda.saveTutor(tutor);
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
        int id = dbda.getStudentIDByEmail(student.getEmail());
        boolean actual = dbda.deleteStudent(id);
        assertTrue(actual);
    }

    @AfterClass
    public static void testDeleteTutor() {
        Tutor tutor = MockData.getTutorObject();
        int id = dbda.getTutorIDByEmail(tutor.getEmail());
        boolean actual = dbda.deleteStudent(id);
        assertTrue(actual);
    }
}