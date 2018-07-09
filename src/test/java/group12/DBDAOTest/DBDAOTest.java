package group12.DBDAOTest;

import group12.DBConnection.DBDAO;
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
    public void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", DBDAO.class);
    }

    @Test
    public void testRegStudentRightInfo() {
        Student student = MockData.getStudentObject();
        Boolean actual = dbda.regStudent(student);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegStudentReEmail() {
        Student student = MockData.getStudentObject();
        Boolean actual = dbda.regStudent(student);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegStudentRePhone() {
        Student student = MockData.getStudentObject();
        student.setEmail("testemailStu2@gmail.com");
        Boolean actual = dbda.regStudent(student);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        Boolean actual = dbda.regTutor(tutor);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testRegTutorReEmail() {
        Tutor tutor = MockData.getTutorObject();
        tutor.setEmail("testemailTut2@gmail.com");
        Boolean actual = dbda.regTutor(tutor);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void testIsEmailNewWithNewEmail() {
        Boolean actual = dbda.isEmailNew(MockData.getNewEmail());
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testIsEmailNewWithUsedEmail() {
        boolean actual = dbda.isEmailNew(MockData.getUsedEmail());
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void testIsPhoneNewWithNewPhone() {
        Boolean actual = dbda.isEmailNew(MockData.getNewPhone());
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void testIsPhoneNewWithUsedPhone() {
        Boolean actual = dbda.isEmailNew(MockData.getUsedPhone());
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStudentId() {
       // Student student = dbda.getStudentId(MockData.getStudentObject().getEmail());
    //    assertEquals(MockData.getStudentObject().getFirstName(), student.getFirstName());
    }

    @Test
    public void testGetTutorId() {
     //   Tutor tutor = dbda.getStudentId(MockData.getTutorObject().getEmail());
      ///  assertEquals(MockData.getTutorObject().getFirstName(), tutor.getFirstName());
    }

    @Test
    public void testGetStudentIdWrongUser() {
   //     Student student = dbda.getStudentId(MockData.getNewEmail());
    //    assertEquals(MockData.getStudentObject().getFirstName(), student.getFirstName());
    }

    @Test
    public void testGetTutorIdWrongUser() {
   //     Tutor tutor = dbda.getStudentId(MockData.getNewPhone());
     //   assertEquals(MockData.getTutorObject().getFirstName(), tutor.getFirstName());
    }

    @Test
    public void testSaveActivationCode() {
    //    int actual = dbda.saveActivationCode(MockData.getActivationCode());
    //    int expected = 1;
    //    assertEquals(actual, expected);
    }

    @Test
    public void activateStudent() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getActivationCode());
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void activateStudentWrongActivation() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getWrongActivationCode());
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void activateStudentOldActivation() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getOldActivationCode());
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void activateTutor() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateStudent(tutorId, MockData.getActivationCode());
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void activateTutorWrongActivation() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateStudent(tutorId, MockData.getWrongActivationCode());
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void activateTutorOldActivation() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateStudent(tutorId, MockData.getOldActivationCode());
        boolean expected = true;
        assertEquals(actual, expected);
    }
}
