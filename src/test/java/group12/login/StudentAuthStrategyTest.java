package group12.login;

import group12.data_access.Student;
import group12.token_auth.JWTAccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentAuthStrategyTest {
    private StudentAuthStrategy str;
    private static String bannedStudentGoTo = "pageA";
    private static String inactiveStudentGoTo = "pageB";
    private static String activeAndUnbannedStudentGoTo = "pageC";

    @Before
    public void setUp() {
        str = new StudentAuthStrategy();
        str.setAuthDAO(new AuthDAOMock());
        str.setBannedStudentGoTo(bannedStudentGoTo);
        str.setActiveAndUnbannedStudentGoTo(activeAndUnbannedStudentGoTo);
        str.setInactiveStudentGoTo(inactiveStudentGoTo);
    }

    @Test
    public void testAuthenticateWithActiveUnbannedUser() {
        Student s = new Student("activeUnbanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("activeUnbanned@email.com");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back, ActiveStudent", response.getMessage());
        assertEquals(activeAndUnbannedStudentGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithInactiveUnbannedUser() {
        Student s = new Student("inactiveUnbanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("inactiveUnbanned@email.com");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back, InactiveStudent", response.getMessage());
        assertEquals(inactiveStudentGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithActiveBannedUser() {
        Student s = new Student("activeBanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("activeBanned@email.com");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back, BannedStudent", response.getMessage());
        assertEquals(bannedStudentGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithInactiveBannedUser() {
        Student s = new Student("inactiveBanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("inactiveBanned@email.com");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back, BannedStudent", response.getMessage());
        assertEquals(bannedStudentGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongEmail() {
        Student s = new Student("wrong@email.com","validpass");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Email", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongPassword() {
        Student s = new Student("activeUnbanned@email.com","wrongpass");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Password", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongEmailAndPassword() {
        Student s = new Student("wrong@email.com","wrongpass");
        str.authenticate(s);
        LoginResponse response = s.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Email", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @After
    public void tearDown() {
        str = null;
    }
}