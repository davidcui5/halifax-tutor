package group12.DBDAOTest;

import group12.data_access.DBDAO;
import group12.data_access.Student;
import group12.data_access.Tutor;
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
        Boolean actual = dbda.saveStudent(student);
        assertTrue(actual);
    }

    @Before
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        boolean actual = dbda.saveTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testIsEmailNewWithNewEmail() {
        int actual = dbda.countOfUserWithEmail(MockData.getNewEmail());
        assertTrue(actual == 0);
    }

    @Test
    public void testIsEmailNewWithUsedEmail() {
        int actual = dbda.countOfUserWithEmail(MockData.getUsedEmail());
        assertFalse(actual < 0);
    }

    @Test
    public void testIsPhoneNewWithNewPhone() {
        int actual = dbda.countOfUserWithPhone(MockData.getNewPhone());
        assertTrue(actual == 0);
    }

    @Test
    public void testIsPhoneNewWithUsedPhone() {
        int actual = dbda.countOfUserWithPhone(MockData.getUsedPhone());
        assertFalse(actual < 0);
    }

    @Test
    public void activateStudent() {
        Student student = MockData.getStudentObject();
        int studentId = dbda.getStudentIDByEmail(student.getEmail());
        boolean actual = dbda.setStudentActivatedStatus(studentId, true);
        assertTrue(actual);
    }

    @Test
    public void activateTutor() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorIDByEmail(tutor.getEmail());
        boolean actual = dbda.setTutorActivatedStatus(tutorId, true);
        assertTrue(actual);
    }

    @After
    public void testDeleteStudent() {
        Student student = MockData.getStudentObject();
        int id = dbda.getStudentIDByEmail(student.getEmail());
        boolean actual = dbda.deleteStudent(id);
        assertTrue(actual);
    }

    @After
    public void testDeleteTutor() {
        Tutor tutor = MockData.getTutorObject();
        int id = dbda.getTutorIDByEmail(tutor.getEmail());
        boolean actual = dbda.deleteTutor(id);
        assertTrue(actual);
    }
}

