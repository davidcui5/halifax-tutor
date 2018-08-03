package group12.search.response;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdentityResponseTest {
    private IdentityResponse response;

    @Before
    public void testSetup() {
        response = new IdentityResponse();
    }

    @Test
    public void testGetSuccess() {
        assertFalse(response.isSuccess());
    }

    @Test
    public void testSetSuccess() {
        boolean oldSuccess = response.isSuccess();
        boolean newSuccess = true;
        assertNotEquals(oldSuccess, newSuccess);
        response.setSuccess(newSuccess);
        assertEquals(newSuccess, response.isSuccess());
    }

    @Test
    public void testGetType() {
        assertEquals(response.getType(), Type.STUDENT);
    }

    @Test
    public void testSetType() {
        Type type = response.getType();
        Type newType = Type.ADMIN;
        assertNotEquals(type, newType);
        response.setType(newType);
        assertEquals(newType, response.getType());
    }

    @After
    public void testTeardown() {
        response = null;
    }
}
