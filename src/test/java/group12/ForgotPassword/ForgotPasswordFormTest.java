package group12.ForgotPassword;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ForgotPasswordFormTest {

    private ForgotPasswordForm form = new ForgotPasswordForm();

    @Test
    public void testEmail(){
        String result = "rh318779@dal.ca";
        form.setEmail("rh318779@dal.ca");
        assertEquals(result, form.getEmail());
    }

    @Test
    public void testPassword(){
        String result = "test password";
        form.setPassword("test password");
        assertEquals(result, form.getPassword());
    }
}
