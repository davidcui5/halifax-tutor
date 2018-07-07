package group12.DBDAOTest;

import group12.DBDAO;
import group12.Registration.Student;
import group12.Registration.Tutor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.rmi.CORBA.StubDelegate;

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
        Student student = MockData.getStudentObject();
        int actual = dbda.regStudent(student);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegStudentReEmail() {
        Student student = MockData.getStudentObject();
        int actual = dbda.regStudent(student);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegStudentRePhone() {
        Student student = MockData.getStudentObject();
        student.setEmail("testemailStu2@gmail.com");
        int actual = dbda.regStudent(student);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        int actual = dbda.regTutor(tutor);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegTutorReEmail() {
        Tutor tutor = MockData.getTutorObject();
        tutor.setEmail("testemailTut2@gmail.com");
        int actual = dbda.regTutor(tutor);
        int expected = 2;
        assertEquals(expected, actual);
    }

}
