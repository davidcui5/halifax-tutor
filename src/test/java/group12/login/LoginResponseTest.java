package group12.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginResponseTest {
    private LoginResponse response;

    @Before
    public void setUp() {
        this.response = new LoginResponse();
    }

    @Test
    public void testResult() {
        response.setResult(AuthenticationResult.SUCCESS);
        assertEquals(AuthenticationResult.SUCCESS, response.getResult());
        response.setResult(AuthenticationResult.FAILURE);
        assertEquals(AuthenticationResult.FAILURE, response.getResult());
    }

    @Test
    public void testMessage() {
        response.setMessage("hello");
        assertEquals("hello", response.getMessage());
    }

    @Test
    public void testUrl() {
        response.setUrl("anUrl");
        assertEquals("anUrl",response.getUrl());
    }

    @Test
    public void testToken() {
        response.setToken("aToken");
        assertEquals("aToken", response.getToken());
    }

    @Test
    public void testToString() {
        response = new LoginResponse(AuthenticationResult.SUCCESS, "hello");
        assertEquals("Login Result: SUCCESS Message: hello Url: null Token: null", response.toString());
    }

    @After
    public void tearDown() {
        response = null;
    }

}