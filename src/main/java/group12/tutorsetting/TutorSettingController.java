package group12.tutorsetting;

import group12.tutorsetting.request.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TutorSettingController {

    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);

    @Autowired
    private TutorSettingService tutorSettingService;

    public TutorSettingController() {
        tutorSettingService = new TutorSettingService();
    }

    @PostMapping(path = "/tutor/setting/password", headers = "content-type=application/json")
    public TutorSettingResponse updatePassword(@RequestBody UpdatePasswordRequest request) {
        return tutorSettingService.getUpdatePasswordResponse(request);
    }

    @PostMapping(path = "/tutor/setting/email", headers = "content-type=application/json")
    public TutorSettingResponse updateEmail(@RequestBody UpdateEmailRequest request) {
        return tutorSettingService.getUpdateEmailResponse(request);
    }

    @PostMapping(path = "/tutor/setting/card", headers = "content-type=application/json")
    public TutorSettingResponse updateCard(@RequestBody UpdateCardRequest request) {
        return tutorSettingService.getUpdateCardResponse(request);
    }

    @PostMapping(path = "/tutor/setting/phone", headers = "content-type=application/json")
    public TutorSettingResponse updatePhone(@RequestBody UpdatePhoneRequest request) {
        return tutorSettingService.getUpdatePhoneResponse(request);
    }

    @PostMapping(path = "/tutor/setting/education", headers = "content-type=application/json")
    public TutorSettingResponse updateEducation(@RequestBody UpdateEducationRequest request) {
        return tutorSettingService.getUpdateEducationResponse(request);
    }

    @PostMapping(path = "/tutor/setting/experience", headers = "content-type=application/json")
    public TutorSettingResponse updateExperience(@RequestBody UpdateExperienceRequest request) {
        return tutorSettingService.getUpdateExperienceResponse(request);
    }

    @PostMapping(path = "/tutor/setting/weeklySchedule", headers = "content-type=application/json")
    public TutorSettingResponse updateWeeklySchedule(@RequestBody UpdateWeeklyScheduleRequest request) {
        return tutorSettingService.getUpdateWeeklyScheduleResponse(request);
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
    public TutorSettingResponse resendConfirmationEmail(@RequestBody ResendConfirmationRequest request) {
        return tutorSettingService.getResendConfirmationEmailResponse(request);
    }

    @PostMapping(path = "/tutor/setting/photo", headers = "content-type=application/json")
    public TutorSettingResponse updateProfilePicture(@RequestBody UpdatePhotoRequest request) {
        return tutorSettingService.getUpdatePhotoResponse(request);
    }

    @GetMapping(path = "/tutor/setting/courses", headers = "content-type=application/json")
    public GetCoursesResponse getCoursesResponse(@RequestParam(value = "token") String token) {
        return tutorSettingService.getGetCoursesResponse(new GetCoursesRequest(token));
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
