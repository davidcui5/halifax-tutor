package group12.token_auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JWTAccessTokenTest {
    private IAccessToken accessToken;

    @Before
    public void testSetup() {
        this.accessToken = JWTAccessToken.getInstance();
    }

    @Test
    public void testTokenWithTrueEmail() {
        String userEmail = "zongming.liu@dal.ca";
        String token = this.accessToken.generateToken(userEmail);

        assertEquals(userEmail, this.accessToken.decodeToken(token));
    }

    @Test
    public void testTokenWithFalseEmail() {
        String userEmail = "zongming.liu@dal.ca";
        String token = this.accessToken.generateToken(userEmail);

        assertNotEquals("xiaoming.liu@dal.ca", this.accessToken.decodeToken(token));
        assertEquals(userEmail, this.accessToken.decodeToken(token));
    }

    @Test
    public void testInvalidToken() {
        String invalidToken = "slkjflksjdkf";
        assertEquals(null, this.accessToken.decodeToken(invalidToken));
    }


    @After
    public void testTeardown() {
        this.accessToken = null;
    }
}
