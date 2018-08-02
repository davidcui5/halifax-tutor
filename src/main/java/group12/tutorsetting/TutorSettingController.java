package group12.tutorsetting;

import group12.tutorsetting.request.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TutorSettingController {

    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);

    @Autowired
    private TutorSettingService tutorSettingService;

    public TutorSettingController() {
        tutorSettingService = new TutorSettingService();
    }

    @PostMapping(path = "/tutor/setting/password", headers = "content-type=application/json")
    public TutorSettingResponse updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdatePasswordResponse(updatePasswordRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/email", headers = "content-type=application/json")
    public TutorSettingResponse updateEmail(@RequestBody UpdateEmailRequest updateEmailRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateEmailResponse(updateEmailRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/card", headers = "content-type=application/json")
    public TutorSettingResponse updateCard(@RequestBody UpdateCardRequest updateCardRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateCardResponse(updateCardRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/phone", headers = "content-type=application/json")
    public TutorSettingResponse updatePhone(@RequestBody UpdatePhoneRequest updatePhoneRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdatePhoneResponse(updatePhoneRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/education", headers = "content-type=application/json")
    public TutorSettingResponse updateEducation(@RequestBody UpdateEducationRequest updateEducationRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateEducationResponse(updateEducationRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/experience", headers = "content-type=application/json")
    public TutorSettingResponse updateExperience(@RequestBody UpdateExperienceRequest updateExperienceRequest) {
        TutorSettingResponse tutorSettingResponse = tutorSettingService.getUpdateExperienceResponse(updateExperienceRequest);
        return tutorSettingResponse;
    }

    @PostMapping(path = "/tutor/setting/weeklySchedule", headers = "content-type=application/json")
    public TutorSettingResponse updateWeeklySchedule(@RequestBody UpdateWeeklyScheduleRequest updateWeeklyScheduleRequest) {
        TutorSettingResponse response = tutorSettingService.getUpdateWeeklyScheduleResponse(updateWeeklyScheduleRequest);
        return response;
    }

    @PostMapping(path = "/tutor/setting/plan", headers = "content-type=application/json")
    public TutorSettingResponse updatePlan(@RequestBody UpdatePlanRequest request) {
        return tutorSettingService.getUpdatePlanResponse(request);
    }

    @PostMapping(path = "/tutor/setting/cancel", headers = "content-type=application/json")
    public TutorSettingResponse cancelPlan(@RequestBody CancelSubscriptionRequest request) {
        return tutorSettingService.getCancelSubscriptionResponse(request);
    }

    @PostMapping(path = "/tutor/setting/resend", headers = "content-type=application/json")
    public TutorSettingResponse resendConfirmationEmail(@RequestBody ResendConfirmationRequest resendConfirmationRequest) {
        TutorSettingResponse tutorSettingResponse = tutorSettingService.getResendConfirmationEmailResponse(resendConfirmationRequest);
        return tutorSettingResponse;
    }

    @PostMapping(path = "/tutor/setting/photo", headers = "content-type=application/json")
    public TutorSettingResponse updateProfilePicture(@RequestBody UpdatePhotoRequest updatePhotoRequest) {
        TutorSettingResponse tutorSettingResponse = tutorSettingService.getUpdatePhotoResponse(updatePhotoRequest);
        return tutorSettingResponse;
    }

    @PostMapping(path = "/tutor/setting/courses", headers = "content-type=application/json")
    public GetCoursesResponse getCoursesResponse(@RequestBody GetCoursesRequest request) {
        return tutorSettingService.getGetCoursesResponse(request);
    }

    @PostMapping(path = "/tutor/setting/courseRemoval", headers = "content-type=application/json")
    public TutorSettingResponse getRemoveCourseResponse(@RequestBody RemoveCourseRequest request) {
        return tutorSettingService.getRemoveCourseResponse(request);
    }

    @PostMapping(path = "/tutor/setting/courseAddition", headers = "content-type=application/json")
    public TutorSettingResponse getAddCourseResponse(@RequestBody AddCourseRequest request) {
        return tutorSettingService.getAddCourseResponse(request);
    }
}
