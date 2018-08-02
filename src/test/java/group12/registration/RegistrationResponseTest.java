package group12.registration;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationResponseTest {

    @Test
    public void testDetail() {
        RegistrationResponse r = new RegistrationResponse(null,"detail");
        assertEquals("detail",r.getDetail());
        r.setDetail(null);
        assertEquals(null,r.getDetail());
    }

    @Test
    public void testResult() {
        RegistrationResponse r = new RegistrationResponse("result",null);
        assertEquals("result",r.getResult());
        r.setResult(null);
        assertEquals(null,r.getResult());

    }
}