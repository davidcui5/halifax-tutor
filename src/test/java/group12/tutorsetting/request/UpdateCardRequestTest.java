package group12.tutorsetting.request;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UpdateCardRequestTest {
    private UpdateCardRequest request;

    @Before
    public void testSetup() {
        request = new UpdateCardRequest();
    }

    @Test
    public void testGetToken() {
        assertNull(request.getToken());
    }

    @Test
    public void testSetToken() {
        String token = "alksjdkfjlsjdf";
        request.setToken(token);
        assertEquals(token, request.getToken());
    }

    @Test
    public void testGetHolderName() {
        assertNull(request.getHolderName());
    }

    @Test
    public void testSetHolderName() {
        String holderName = "ZL";
        request.setHolderName(holderName);
        assertEquals(holderName, request.getHolderName());
    }

    @Test
    public void testGetCreditCardNumber() {
        assertNull(request.getCreditCardNumber());
    }

    @Test
    public void testSetCreditCardNumber() {
        String number = "4444";
        request.setCreditCardNumber(number);
        assertEquals(number, request.getCreditCardNumber());
    }

    @Test
    public void testGetExpireDate() {
        assertNull(request.getExpireDate());
    }

    @Test
    public void testSetExpireDate() {
        String expireDate = "2018";
        request.setExpireDate(expireDate);
        assertEquals(expireDate, request.getExpireDate());
    }

    @Test
    public void testGetSecurityCode() {
        assertEquals(0, request.getSecurityCode());
    }

    @Test
    public void testSetSecurityCode() {
        int code = 444;
        request.setSecurityCode(code);
        assertEquals(code, request.getSecurityCode());
    }

    @After
    public void testTeardown() {
        request = null;
    }
}
