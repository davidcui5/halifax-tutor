package group12.login;

import group12.dataaccess.Tutor;
import group12.tokenauth.JWTAccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TutorAuthStrategyTest {
    private TutorAuthStrategy str;
    private static String bannedTutorGoTo = "pageA";
    private static String inactiveTutorGoTo = "pageB";
    private static String activeAndUnbannedTutorGoTo = "pageC";

    @Before
    public void setUp() {
        str = new TutorAuthStrategy();
        str.setAuthDAO(new AuthDAOMock());
        str.setBannedTutorGoTo(bannedTutorGoTo);
        str.setActiveAndUnbannedTutorGoTo(activeAndUnbannedTutorGoTo);
        str.setInactiveTutorGoTo(inactiveTutorGoTo);
    }

    @Test
    public void testAuthenticateWithActiveUnbannedUser() {
        Tutor t = new Tutor("activeUnbanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("activeUnbanned@email.com");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back ActiveTutor", response.getMessage());
        assertEquals(activeAndUnbannedTutorGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithInactiveUnbannedUser() {
        Tutor t = new Tutor("inactiveUnbanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("inactiveUnbanned@email.com");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back InactiveTutor", response.getMessage());
        assertEquals(inactiveTutorGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithActiveBannedUser() {
        Tutor t = new Tutor("activeBanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("activeBanned@email.com");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back BannedTutor", response.getMessage());
        assertEquals(bannedTutorGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithInactiveBannedUser() {
        Tutor t = new Tutor("inactiveBanned@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("inactiveBanned@email.com");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back BannedTutor", response.getMessage());
        assertEquals(bannedTutorGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongEmail() {
        Tutor t = new Tutor("wrong@email.com","validpass");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Email", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongPassword() {
        Tutor t = new Tutor("activeUnbanned@email.com","wrongpass");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Password", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongEmailAndPassword() {
        Tutor t = new Tutor("wrong@email.com","wrongpass");
        str.authenticate(t);
        LoginResponse response = t.getLoginResponse();

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