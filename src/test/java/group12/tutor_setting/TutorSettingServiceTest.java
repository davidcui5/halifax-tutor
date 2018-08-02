package group12.tutor_setting;

import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import group12.tutor_setting.request.UpdatePasswordRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TutorSettingServiceTest {
    private TutorSettingService tutorSettingService;
    private IAccessToken accessToken;

    @Before
    public void testSetup() {
        tutorSettingService = new TutorSettingService();
        accessToken = JWTAccessToken.getInstance();
        tutorSettingService.setTutorSettingDAO(new TutorSettingDAOMock());
        tutorSettingService.setAccessToken(accessToken);
    }

    @Test
    public void testGetUpdatePasswordResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        UpdatePasswordRequest request = new UpdatePasswordRequest(token, email);

        assertTrue(tutorSettingService.getUpdatePasswordResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);
        request = new UpdatePasswordRequest(token, email);

        assertFalse(tutorSettingService.getUpdatePasswordResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdateEmailResponse() {

    }



    @After
    public void testTeardown() {
        tutorSettingService = null;
    }
}
