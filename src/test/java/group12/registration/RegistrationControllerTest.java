package group12.registration;

import group12.dataaccess.Student;
import group12.dataaccess.Tutor;
import group12.encryption.IEncryptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationControllerTest {
    private RegistrationController controller;
    private IRegister service;
    private IEncryptor encryptor;

    @Before
    public void setUp() {
        service = new RegistrationServiceMock();
        encryptor = new EncryptorMock();
        controller = new RegistrationController(service,encryptor);
    }

    @Test
    public void testRegisterStudent() {
        Student s = new Student();
        s.setPassword("pass");
        RegistrationResponse r = controller.registerStudent(s);
        assertEquals("SUCCESS",r.getResult());
        assertEquals("registered",r.getDetail());
    }

    @Test
    public void testRegisterTutor() {
        Tutor t = new Tutor();
        t.setPassword("pass");
        RegistrationResponse r = controller.registerTutor(t);
        assertEquals("SUCCESS",r.getResult());
        assertEquals("registered",r.getDetail());
    }

    @Test
    public void testActivateStudent() {
        assertEquals("activated",controller.activateStudent(1,"code"));
    }

    @Test
    public void testActivateTutor() {
        assertEquals("activated",controller.activateTutor(1,"code"));
    }

    @After
    public void tearDown() {
        controller = null;
        service = null;
        encryptor = null;
    }
}