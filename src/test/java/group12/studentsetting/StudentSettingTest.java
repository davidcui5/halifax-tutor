package group12.studentsetting;

import group12.DBDAOTest.MockData;
import group12.dataaccess.IDataAccessObject;
import group12.dataaccess.MysqlDAOImpl;
import group12.dataaccess.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertTrue;

public class StudentSettingTest {
    static ClassPathXmlApplicationContext context;
    static IDataAccessObject dbda;

    @BeforeClass
    public static void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", MysqlDAOImpl.class);
    }

    @Before
    public void testRegStudentRightInfo() {
        Student student = MockData.getStudentObject();
        boolean actual = dbda.saveStudent(student);
        assertTrue(actual);
    }

    @Test
    public void testChangePassword() {
        Student student = MockData.getStudentObject();
        boolean actual = dbda.updateStudentPassword(student.getEmail(), MockData.getPasswordForTest());
        assertTrue(actual);
        student = dbda.getStudentByEmail(student.getEmail());
        actual = student.getPassword().equals(MockData.getPasswordForTest());
        assertTrue(actual);
    }

    @Test
    public void testChangePhone() {
        Student student = MockData.getStudentObject();
        boolean actual = dbda.updateStudentPhone(student.getEmail(), MockData.getPhoneForTest());
        assertTrue(actual);
        student = dbda.getStudentByEmail(student.getEmail());
        actual = student.getPhoneNumber().equals(MockData.getPhoneForTest());
        assertTrue(actual);
    }

    @Test
    public void testChangeEmail() {
        Student student = MockData.getStudentObject();
        String oldEmail = student.getEmail();
        student = dbda.getStudentByEmail(oldEmail);
        boolean actual = dbda.updateStudentEmail(student.getEmail(), MockData.getEmailForTest());
        assertTrue(actual);
        Student studentNewEmail = dbda.getStudentByEmail(MockData.getEmailForTest());
        actual = student.getStudentID() == studentNewEmail.getStudentID();
        assertTrue(actual);
        actual = dbda.updateStudentEmail(studentNewEmail.getEmail(), student.getEmail());
        assertTrue(actual);
    }

    @After
    public void testDeleteStudent() {
        Student student = MockData.getStudentObject();
        int id = dbda.getStudentIDByEmail(student.getEmail());
        boolean actual = dbda.deleteStudent(id);
        assertTrue(actual);
    }
}
