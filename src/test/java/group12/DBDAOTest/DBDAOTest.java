package group12.DBDAOTest;

import group12.DBDAO;
import group12.Registration.Student;
import group12.Registration.Tutor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DBDAOTest {
    ClassPathXmlApplicationContext context;
    DBDAO dbda;

    @Before
    private void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", DBDAO.class);
    }

    @Test
    public void testRegStudentRightInfo() {
        Student student = new Student();
        student.setEmail("testemail@gmail.com");
        student.setFirstName("testName");
        student.setLastName("testLName");
        student.setSchool("testSchool");
        student.setPhoneNumber("9021234567");
        student.setPassword("pass123456");
        int result = dbda.regStudent(student);
        int expected = 1;
        assertEquals(result,1);
    }

    @Test
    public void testRegStudentReInfoEmail() {
        Student student = new Student();
        student.setEmail("testemail@gmail.com");
        student.setFirstName("testName");
        student.setLastName("testLName");
        student.setSchool("testSchool");
        student.setPhoneNumber("9021234567");
        student.setPassword("pass123456");
        int result = dbda.regStudent(student);
        int expected = 1;
        assertEquals(result,1);
    }
}
