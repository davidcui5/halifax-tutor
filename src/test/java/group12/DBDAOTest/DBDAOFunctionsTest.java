package group12.DBDAOTest;

import group12.dataaccess.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DBDAOFunctionsTest {
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

    @Before
    public void testRegTutorRightInfo() {
        Tutor tutor = MockData.getTutorObject();
        boolean actual = dbda.saveTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testIsEmailNewWithNewEmail() {
        int actual = dbda.countOfUserWithEmail(MockData.getNewEmail());
        assertEquals(0, actual);
    }

    @Test
    public void testIsEmailNewWithUsedEmail() {
        int actual = dbda.countOfUserWithEmail(MockData.getUsedEmail());
        assertFalse(actual < 0);
    }

    @Test
    public void testIsPhoneNewWithNewPhone() {
        int actual = dbda.countOfUserWithPhone(MockData.getNewPhone());
        assertEquals(0, actual);
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

    @Test
    public void testCourseAndSetTOTutor() {
        Tutor tutor = MockData.getTutorObject();
        int tutorId = dbda.getTutorIDByEmail(tutor.getEmail());
        if (dbda.numberOfCourse(MockData.getCourseObject().getName()) == 0) {
            boolean actual = dbda.saveCourse(MockData.getCourseObject());
            assertTrue(actual);
        }
        Course course = dbda.getCourseByName(MockData.getCourseObject().getName());
        assertEquals(course.getName(), MockData.getCourseObject().getName());
        boolean actual = dbda.setCourseToTutor(tutorId, course.getId(), 5);
        assertTrue(actual);
        List<Course> courses = dbda.getCoursesOFTutor(tutorId);
        assertEquals(1, courses.size());
    }

    @Test
    public void getAllCourses() {
        List<Course> courses = dbda.getAllCourses();
        assertTrue(courses.size() > 0);
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

