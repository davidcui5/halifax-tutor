package group12.DBDAOTest;

import group12.DBConnection.DBDAO;
import group12.Registration.Student;
import group12.Registration.Tutor;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DBDAOFunctionsTest {
    static ClassPathXmlApplicationContext context;
    static DBDAO dbda;

    @BeforeClass
    public static void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", DBDAO.class);
    }

    @Before
    public void testRegStudentRightInfo() {
        Student student = MockData.getStudentObject();
        Boolean actual = dbda.regStudent(student);
        assertTrue(actual);
    }

    @Before
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        boolean actual = dbda.regTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testIsEmailNewWithNewEmail() {
        boolean actual = dbda.isEmailNew(MockData.getNewEmail());
        assertTrue(actual);
    }

    @Test
    public void testIsEmailNewWithUsedEmail() {
        boolean actual = dbda.isEmailNew(MockData.getUsedEmail());
        assertFalse(actual);
    }

    @Test
    public void testIsPhoneNewWithNewPhone() {
        boolean actual = dbda.isEmailNew(MockData.getNewPhone());
        assertTrue(actual);
    }

    @Test
    public void testIsPhoneNewWithUsedPhone() {
        boolean actual = dbda.isPhoneNumberNew(MockData.getUsedPhone());
        assertFalse(actual);
    }

    @Test
    public void activateStudent() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getActivationCode());
        assertTrue(actual);
    }

    @Test
    public void activateStudentWrongActivation() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getWrongActivationCode());
        assertFalse(actual);
    }

    @Test
    public void activateStudentOldActivation() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getOldActivationCode());
        assertFalse(actual);
    }

    @Test
    public void activateTutor() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateStudent(tutorId, MockData.getActivationCode());
        assertTrue(actual);
    }

    @Test
    public void activateTutorWrongActivation() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateStudent(tutorId, MockData.getWrongActivationCode());
        assertFalse(actual);
    }

    @Test
    public void activateTutorOldActivation() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateStudent(tutorId, MockData.getOldActivationCode());
        assertTrue(actual);
    }

    @After
    public void testDeleteStudent() {
        Student student = MockData.getStudentObject();
        int id = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.delelteStudent(id);
        assertTrue(actual);
    }

    @After
    public void testDeleteTutor() {
        Tutor tutor = MockData.getTutorObject();
        int id = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.delelteTutor(id);
        assertTrue(actual);
    }
}
