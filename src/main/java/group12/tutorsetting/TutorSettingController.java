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
        logger.info("Update password request received: " + request.toString());
        return tutorSettingService.getUpdatePasswordResponse(request);
    }

    @PostMapping(path = "/tutor/setting/email", headers = "content-type=application/json")
    public TutorSettingResponse updateEmail(@RequestBody UpdateEmailRequest request) {
        logger.info("Update email request received: " + request.toString());
        return tutorSettingService.getUpdateEmailResponse(request);
    }

    @PostMapping(path = "/tutor/setting/card", headers = "content-type=application/json")
    public TutorSettingResponse updateCard(@RequestBody UpdateCardRequest request) {
        logger.info("Update card request received: " + request.toString());
        return tutorSettingService.getUpdateCardResponse(request);
    }

    @PostMapping(path = "/tutor/setting/phone", headers = "content-type=application/json")
    public TutorSettingResponse updatePhone(@RequestBody UpdatePhoneRequest request) {
        logger.info("Update phone request received: " + request.toString());
        return tutorSettingService.getUpdatePhoneResponse(request);
    }

    @PostMapping(path = "/tutor/setting/education", headers = "content-type=application/json")
    public TutorSettingResponse updateEducation(@RequestBody UpdateEducationRequest request) {
        logger.info("Update education request received: " + request.toString());
        return tutorSettingService.getUpdateEducationResponse(request);
    }

    @PostMapping(path = "/tutor/setting/experience", headers = "content-type=application/json")
    public TutorSettingResponse updateExperience(@RequestBody UpdateExperienceRequest request) {
        logger.info("Update experience request received: " + request.toString());
        return tutorSettingService.getUpdateExperienceResponse(request);
    }

    @PostMapping(path = "/tutor/setting/weeklySchedule", headers = "content-type=application/json")
    public TutorSettingResponse updateWeeklySchedule(@RequestBody UpdateWeeklyScheduleRequest request) {
        logger.info("Update weekly schedule request received: " + request.toString());
        return tutorSettingService.getUpdateWeeklyScheduleResponse(request);
    }

    @PostMapping(path = "/tutor/setting/plan", headers = "content-type=application/json")
    public TutorSettingResponse changePlan(@RequestBody UpdatePlanRequest request) {
        logger.info("Change plan request received: " + request.toString());
        return tutorSettingService.getUpdatePlanResponse(request);
    }

    @DeleteMapping(path = "/tutor/setting/plan", headers = "content-type=application/json")
    public TutorSettingResponse cancelPlan(@RequestBody CancelSubscriptionRequest request) {
        logger.info("Cancel plan request received: " + request.toString());
        return tutorSettingService.getCancelSubscriptionResponse(request);
    }

    @PostMapping(path = "/tutor/setting/resend", headers = "content-type=application/json")
    public TutorSettingResponse resendConfirmationEmail(@RequestBody ResendConfirmationRequest request) {
        logger.info("Resend confirmation email: " + request.toString());
        return tutorSettingService.getResendConfirmationEmailResponse(request);
    }

    @PostMapping(path = "/tutor/setting/photo", headers = "content-type=application/json")
    public TutorSettingResponse updateProfilePicture(@RequestBody UpdatePhotoRequest request) {
        logger.info("Update photo request received: " + request.toString());
        return tutorSettingService.getUpdatePhotoResponse(request);
    }

    @GetMapping(path = "/tutor/setting/courses", headers = "content-type=application/json")
    public GetCoursesResponse getTutorCourses(@RequestParam(value = "token") String token) {
        logger.info("Get all courses of tutor request received with token = " + token);
        return tutorSettingService.getGetCoursesResponse(new GetCoursesRequest(token));
    }

    @DeleteMapping(path = "/tutor/setting/course", headers = "content-type=application/json")
    public TutorSettingResponse removeTutorCourse(@RequestBody RemoveCourseRequest request) {
        logger.info("Remove course request received: " + request.toString());
        return tutorSettingService.getRemoveCourseResponse(request);
    }

    @PostMapping(path = "/tutor/setting/course", headers = "content-type=application/json")
    public TutorSettingResponse addTutorCourse(@RequestBody AddCourseRequest request) {
        logger.info("Add course for tutor request received: " + request.toString());
        return tutorSettingService.getAddCourseResponse(request);
    }
}
