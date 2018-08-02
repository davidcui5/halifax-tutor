package group12.registration;

import group12.dataaccess.Student;
import group12.dataaccess.Tutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationServiceTest {
    private RegistrationService service;

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final String ERROR = "ERROR";
    private static final String LOGIN_PAGE_URL = "../index.html";
    private static final String CODE_EXPIRED_PAGE = "redirect:/html/code-expired.html";
    private static final String LOGIN_PAGE = "redirect:/index.html";
    private static final String ERROR_PAGE = "redirect:/html/exception-page.html";
    private static final String REPEAT_EMAIL = "Email already registered";
    private static final String REPEAT_PHONE = "Phone already registered";
    private static final String REPEAT_CARD = "Card already registered";

    @Before
    public void setUp() {
        service = new RegistrationService();
        service.setDao(new dataAccessObjectMock());
        service.setMailer(new MailerMock());
        service.setEmailSender("sender");
        service.setServerURL("url");
    }

    @Test
    public void testRegisterStudentWithValidStudent() {
        Student s = new Student();
        s.setEmail("new@email.com");
        s.setPassword("saveSuccess");
        s.setPhoneNumber("1234567890");
        RegistrationResponse r = service.registerStudent(s);

        assertEquals(SUCCESS,r.getResult());
        assertEquals(LOGIN_PAGE_URL,r.getDetail());
    }

    @Test
    public void testRegisterStudentWithRepeatEmail() {
        Student s = new Student();
        s.setEmail("repeat@email.com");
        s.setPassword("saveSuccess");
        s.setPhoneNumber("1234567890");
        RegistrationResponse r = service.registerStudent(s);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_EMAIL,r.getDetail());
    }

    @Test
    public void testRegisterStudentWithRepeatPhone() {
        Student s = new Student();
        s.setEmail("new@email.com");
        s.setPassword("saveSuccess");
        s.setPhoneNumber("1112223333");
        RegistrationResponse r = service.registerStudent(s);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_PHONE,r.getDetail());
    }

    @Test
    public void testRegisterStudentWithRepeatEmailAndPhone() {
        Student s = new Student();
        s.setEmail("repeat@email.com");
        s.setPassword("saveSuccess");
        s.setPhoneNumber("1112223333");
        RegistrationResponse r = service.registerStudent(s);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_EMAIL,r.getDetail());
    }

    @Test
    public void testRegisterStudentWithSaveFailure() {
        Student s = new Student();
        s.setEmail("new@email.com");
        s.setPassword("saveWentWrong");
        s.setPhoneNumber("1234567890");
        RegistrationResponse r = service.registerStudent(s);

        assertEquals(FAILURE,r.getResult());
        assertEquals(ERROR,r.getDetail());
    }

    @Test
    public void testRegisterTutorWithValidTutor() {
        Tutor t = new Tutor();
        t.setEmail("new@email.com");
        t.setPassword("saveSuccess");
        t.setPhoneNumber("1234567890");
        t.setCreditCardNum("1234567812345678");

        RegistrationResponse r = service.registerTutor(t);

        assertEquals(SUCCESS,r.getResult());
        assertEquals(LOGIN_PAGE_URL,r.getDetail());
    }

    @Test
    public void testRegisterTutorWithRepeatEmail() {
        Tutor t = new Tutor();
        t.setEmail("repeat@email.com");
        t.setPassword("saveSuccess");
        t.setPhoneNumber("1234567890");
        t.setCreditCardNum("1234567812345678");

        RegistrationResponse r = service.registerTutor(t);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_EMAIL,r.getDetail());
    }

    @Test
    public void testRegisterTutorWithRepeatPhone() {
        Tutor t = new Tutor();
        t.setEmail("new@email.com");
        t.setPassword("saveSuccess");
        t.setPhoneNumber("1112223333");
        t.setCreditCardNum("1234567812345678");

        RegistrationResponse r = service.registerTutor(t);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_PHONE,r.getDetail());
    }

    @Test
    public void testRegisterTutorWithRepeatCard() {
        Tutor t = new Tutor();
        t.setEmail("new@email.com");
        t.setPassword("saveSuccess");
        t.setPhoneNumber("1234567890");
        t.setCreditCardNum("1111222211112222");

        RegistrationResponse r = service.registerTutor(t);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_CARD,r.getDetail());
    }

    @Test
    public void testRegisterTutorWithRepeatAll() {
        Tutor t = new Tutor();
        t.setEmail("repeat@email.com");
        t.setPassword("saveSuccess");
        t.setPhoneNumber("1112223333");
        t.setCreditCardNum("1111222211112222");

        RegistrationResponse r = service.registerTutor(t);

        assertEquals(FAILURE,r.getResult());
        assertEquals(REPEAT_EMAIL,r.getDetail());
    }

    @Test
    public void testRegisterTutorWithSaveFailure() {
        Tutor t = new Tutor();
        t.setEmail("new@email.com");
        t.setPassword("save went wrong");
        t.setPhoneNumber("1234567890");
        t.setCreditCardNum("1234567812345678");

        RegistrationResponse r = service.registerTutor(t);

        assertEquals(FAILURE,r.getResult());
        assertEquals(ERROR,r.getDetail());
    }

    @Test
    public void testActivateStudent() {
        assertEquals(LOGIN_PAGE,service.activateStudent(100,"validCode"));
        assertEquals(CODE_EXPIRED_PAGE,service.activateStudent(100,"expiredCode"));
        assertEquals(CODE_EXPIRED_PAGE,service.activateStudent(0,"expiredCode"));
        assertEquals(ERROR_PAGE,service.activateStudent(0,"validCode"));
    }

    @Test
    public void testActivateTutor() {
        assertEquals(LOGIN_PAGE,service.activateTutor(100,"validCode"));
        assertEquals(CODE_EXPIRED_PAGE,service.activateTutor(100,"expiredCode"));
        assertEquals(CODE_EXPIRED_PAGE,service.activateTutor(0,"expiredCode"));
        assertEquals(ERROR_PAGE,service.activateTutor(0,"validCode"));
    }

    @After
    public void tearDown() {
        service = null;
    }
}