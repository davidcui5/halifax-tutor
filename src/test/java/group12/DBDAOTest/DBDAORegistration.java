
package group12.DBDAOTest;

import group12.data_access.*;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.*;

public class DBDAORegistration {
    static ClassPathXmlApplicationContext context;
    static IDataAccessObject dbda;

    @BeforeClass
    public static void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", MysqlDAOImpl.class);
    }

    @Test
    public void testRegStudentRightInfo() {
        Student student = MockData.getStudentObject();
        int number = dbda.countOfUserWithEmail(student.getEmail());
        assertEquals(0, number);
        number = dbda.countOfUserWithPhone(student.getPhoneNumber());
        assertEquals(0, number);
        boolean actual = dbda.saveStudent(student);
        assertTrue(actual);
    }

    @Test
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        int number = dbda.countOfUserWithCreditCardNum(tutor.getCreditCardNum());
        assertEquals(0, number);
        boolean actual = dbda.saveTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testActivationCode() {
        boolean actual = dbda.saveActivationCode(MockData.getActivationCode());
        assertTrue(actual);
        actual = dbda.deleteActivationCodeByValue(MockData.getActivationCode());
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
        boolean actual = dbda.deleteTutor(id);
        assertTrue(actual);
    }
}
