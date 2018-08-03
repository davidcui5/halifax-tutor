package group12.tutorsetting;

import group12.dataaccess.IDataAccessObject;
import group12.search.dataaccess.DataAccessObjectMock;
import group12.tokenauth.IAccessToken;
import group12.tutorsetting.dataaccess.ITutorSettingDAO;
import group12.tutorsetting.dataaccess.JWTAccessTokenMock;
import group12.tutorsetting.dataaccess.TutorSettingDAOMock;
import group12.tutorsetting.request.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TutorSettingServiceTest {
    private TutorSettingService tutorSettingService;
    private IDataAccessObject dataAccessObject;
    private ITutorSettingDAO tutorSettingDAO;
    private IAccessToken accessToken;


    @Before
    public void testSetup() {
        dataAccessObject = new DataAccessObjectMock();
        tutorSettingDAO = new TutorSettingDAOMock();
        accessToken = new JWTAccessTokenMock();
        tutorSettingService = new TutorSettingService(tutorSettingDAO, dataAccessObject, accessToken);
    }

    @Test
    public void testGetUpdateWeeklyScheduleResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);

        boolean[][] weeklySchedule = new boolean[7][4];
        UpdateWeeklyScheduleRequest request = new UpdateWeeklyScheduleRequest(token, weeklySchedule);
        assertTrue(tutorSettingService.getUpdateWeeklyScheduleResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);
        request = new UpdateWeeklyScheduleRequest(token, weeklySchedule);

        assertFalse(tutorSettingService.getUpdateWeeklyScheduleResponse(request).isSuccess());

    }

    @Test
    public void testGetUpdatePasswordResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        String password = "1234";
        UpdatePasswordRequest request = new UpdatePasswordRequest(token, password);

        assertTrue(tutorSettingService.getUpdatePasswordResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);
        request = new UpdatePasswordRequest(token, password);

        assertFalse(tutorSettingService.getUpdatePasswordResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdateEmailResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);

        UpdateEmailRequest request = new UpdateEmailRequest(token, email);

        assertTrue(tutorSettingService.getUpdateEmailResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);
        request = new UpdateEmailRequest(token, email);

        assertFalse(tutorSettingService.getUpdateEmailResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdatePhoneResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);

        String phone = "123-123-1234";

        UpdatePhoneRequest request = new UpdatePhoneRequest(token, phone);

        assertTrue(tutorSettingService.getUpdatePhoneResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);
        request = new UpdatePhoneRequest(token, phone);

        assertFalse(tutorSettingService.getUpdatePhoneResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdateCardResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);

        String holderName = "Zongming Liu";
        String cardNumber = "1234";
        String expireDate = "2018";
        int code = 444;

        UpdateCardRequest request = new UpdateCardRequest(token, holderName, cardNumber, expireDate, code);

        assertTrue(tutorSettingService.getUpdateCardResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getUpdateCardResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdateEducationResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        String education = "Dalhousie University";

        UpdateEducationRequest request = new UpdateEducationRequest(token, education);

        assertTrue(tutorSettingService.getUpdateEducationResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getUpdateEducationResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdatePhotoResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        String photoURL = "fakeUrl";

        UpdatePhotoRequest request = new UpdatePhotoRequest(token, photoURL);

        assertTrue(tutorSettingService.getUpdatePhotoResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getUpdatePhotoResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdateExperienceResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        String experience = "Dalhousie University - TA";

        UpdateExperienceRequest request = new UpdateExperienceRequest(token, experience);

        assertTrue(tutorSettingService.getUpdateExperienceResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getUpdateExperienceResponse(request).isSuccess());
    }

    @Test
    public void testGetUpdatePlanResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        int planNo = 1;

        UpdatePlanRequest request = new UpdatePlanRequest(token, planNo);

        assertTrue(tutorSettingService.getUpdatePlanResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getUpdatePlanResponse(request).isSuccess());
    }

    @Test
    public void testGetCancelSubscriptionResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);


        TutorSettingRequest request = new TutorSettingRequest(token);

        assertTrue(tutorSettingService.getCancelSubscriptionResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getCancelSubscriptionResponse(request).isSuccess());
    }

    @Test
    public void testGetGetCoursesResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);

        TutorSettingRequest request = new TutorSettingRequest(token);

        assertTrue(tutorSettingService.getGetCoursesResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getGetCoursesResponse(request).isSuccess());
    }

    @Test
    public void testGetRemoveCourseResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        String school = "DAL";
        String courseCode = "CSCI5308";

        RemoveCourseRequest request = new RemoveCourseRequest(token, school, courseCode);

        assertTrue(tutorSettingService.getRemoveCourseResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getRemoveCourseResponse(request).isSuccess());
    }


    @Test
    public void testGetAddCourseResponse() {
        String email = "zongming.liu@dal.ca";
        String token = accessToken.generateToken(email);
        String school = "DAL";
        String courseCode = "CSCI5308";
        float coursePrice = 49.0f;

        AddCourseRequest request = new AddCourseRequest(token, school, courseCode, coursePrice);

        assertTrue(tutorSettingService.getAddCourseResponse(request).isSuccess());

        email = "haha.li@dal.ca";
        token = accessToken.generateToken(email);

        request.setToken(token);

        assertFalse(tutorSettingService.getAddCourseResponse(request).isSuccess());
    }



    @After
    public void testTeardown() {
        tutorSettingService = null;
    }
}
