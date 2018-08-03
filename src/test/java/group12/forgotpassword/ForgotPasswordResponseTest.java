package group12.forgotpassword;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ForgotPasswordResponseTest {

    private ForgotPasswordResponse response = new ForgotPasswordResponse();

    @Test
    public void testResult(){
        String result = "Success";
        response.setResult("Success");
        assertEquals(result, response.getResult());
    }

    @Test
    public void testDetails(){
        ArrayList<String> details = new ArrayList<>();
        details.add("Test1");
        response.addDetail("Test1");
        assertEquals(details, response.getDetails());
    }
}
