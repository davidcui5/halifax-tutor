package group12.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LoginControllerTest {
    private LoginController controller;

    @Before
    public void setUp() {
        Map<String, IAuthenticationStrategy> strategies = new HashMap<>();
        strategies.put("student", new AuthStrategyMock());
        strategies.put("tutor", new AuthStrategyMock());
        strategies.put("admin", new AuthStrategyMock());
        controller = new LoginController(strategies);
    }

    @Test
    public void testStudentLogin(){
        Map<String, String> user = new HashMap<>();
        user.put("type","student");
        user.put("email","abc@dal.ca");
        user.put("password","123456");
        LoginResponse response = controller.login(user);

        assertEquals(AuthenticationResult.SUCCESS,response.getResult());
        assertEquals("welcome back", response.getMessage());
        assertEquals("url", response.getUrl());
        assertEquals("token", response.getToken());
    }

    @Test
    public void testTutorLogin(){
        Map<String, String> user = new HashMap<>();
        user.put("type","tutor");
        user.put("email","abc@dal.ca");
        user.put("password","123456");
        LoginResponse response = controller.login(user);

        assertEquals(AuthenticationResult.SUCCESS,response.getResult());
        assertEquals("welcome back", response.getMessage());
        assertEquals("url", response.getUrl());
        assertEquals("token", response.getToken());
    }

    @Test
    public void testAdminLogin(){
        Map<String, String> user = new HashMap<>();
        user.put("type","admin");
        user.put("email","abc@dal.ca");
        user.put("password","123456");
        LoginResponse response = controller.login(user);

        assertEquals(AuthenticationResult.SUCCESS,response.getResult());
        assertEquals("welcome back", response.getMessage());
        assertEquals("url", response.getUrl());
        assertEquals("token", response.getToken());
    }

    @After
    public void tearDown() {
        controller = null;
    }
}