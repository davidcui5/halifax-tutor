package group12.DBDAOTest;

import group12.data_access.DBDAO;
import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;
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
        StudentSignupForm student = MockData.getStudentObject();
        Boolean actual = dbda.regStudent(student);
        assertTrue(actual);
    }

    @Before
    public void testRegTutorRightInfo() {
        TutorSignupForm tutor = MockData.getTutorObject();
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
        StudentSignupForm student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getActivationCode());
        assertTrue(actual);
    }

    @Test
    public void activateStudentWrongActivation() {
        StudentSignupForm student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getWrongActivationCode());
        assertFalse(actual);
    }

    @Test
    public void activateStudentOldActivation() {
        StudentSignupForm student = MockData.getStudentObject();
        int studentId = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.activateStudent(studentId, MockData.getOldActivationCode());
        assertFalse(actual);
    }

    @Test
    public void activateTutor() {
        TutorSignupForm tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateTutor(tutorId, MockData.getActivationCode());
        assertTrue(actual);
    }

    @Test
    public void activateTutorWrongActivation() {
        TutorSignupForm tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateTutor(tutorId, MockData.getWrongActivationCode());
        assertFalse(actual);
    }

    @Test
    public void activateTutorOldActivation() {
        TutorSignupForm tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.activateTutor(tutorId, MockData.getOldActivationCode());
        assertFalse(actual);
    }

    @After
    public void testDeleteStudent() {
        StudentSignupForm student = MockData.getStudentObject();
        int id = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.deleteStudent(id);
        assertTrue(actual);
    }

    @After
    public void testDeleteTutor() {
        TutorSignupForm tutor = MockData.getTutorObject();
        int id = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.deleteTutor(id);
        assertTrue(actual);
    }
}

