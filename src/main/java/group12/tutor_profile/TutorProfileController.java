package group12.tutor_profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TutorProfileController {
    @Autowired
    private iTutorProfile iTutorProfile;

    @PostMapping(path = "/gettutorprofiledata")
    public @ResponseBody TutorProfileResponse getTutorProfileData(@RequestBody TutorProfileForm tutorProfile){
        TutorProfileResponse response = iTutorProfile.getTutorProfile(tutorProfile.getId());
        return response;
    }

    @PostMapping(path = "/sendtutormessage")
    public @ResponseBody TutorProfileResponse sendTutorMessage(@RequestBody TutorProfileForm tutorProfile){
        TutorProfileResponse response = iTutorProfile.sendMessage(tutorProfile);
        return response;
    }

    @PostMapping(path = "/sendtutorfeedback")
    public @ResponseBody TutorProfileResponse sendTutorFeedback(@RequestBody TutorProfileForm tutorProfile){
        TutorProfileResponse response = iTutorProfile.sendFeedback(tutorProfile);
        return response;
    }

}
