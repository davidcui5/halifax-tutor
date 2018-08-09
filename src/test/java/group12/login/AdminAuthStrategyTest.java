package group12.login;

import group12.dataaccess.Admin;
import group12.tokenauth.JWTAccessToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminAuthStrategyTest {
    private AdminAuthStrategy str;
    private static String adminGoTo = "adminPage";

    @Before
    public void setUp() {
        str = new AdminAuthStrategy();
        str.setAuthDAO(new AuthDAOMock());
        str.setAdminGoTo(adminGoTo);
    }

    @Test
    public void testAuthenticateWithCorrectCredential() {
        Admin admin = new Admin("valid@email.com","validpass");
        String token = JWTAccessToken.getInstance().generateToken("valid@email.com");
        str.authenticate(admin);
        LoginResponse response = admin.getLoginResponse();

        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        assertEquals("Welcome Back Admin", response.getMessage());
        assertEquals(adminGoTo, response.getUrl());
        assertEquals(token, response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongEmail() {
        Admin admin = new Admin("wrong@email.com","validpass");
        str.authenticate(admin);
        LoginResponse response = admin.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Email", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongPassword() {
        Admin admin = new Admin("valid@email.com","wrongpass");
        str.authenticate(admin);
        LoginResponse response = admin.getLoginResponse();

        assertEquals(AuthenticationResult.FAILURE, response.getResult());
        assertEquals("Wrong Password", response.getMessage());
        assertEquals(null,response.getUrl());
        assertEquals(null,response.getToken());
    }

    @Test
    public void testAuthenticateWithWrongEmailAndPassword() {
        Admin admin = new Admin("wrong@email.com","wrongpass");
        str.authenticate(admin);
        LoginResponse response = admin.getLoginResponse();

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