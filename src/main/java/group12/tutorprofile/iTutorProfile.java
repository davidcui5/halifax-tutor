package group12.tutorprofile;

public interface iTutorProfile {

    TutorProfileResponse getTutorProfile(int tutorId);

    TutorProfileResponse sendMessage(TutorProfileForm tutorProfileForm);

    TutorProfileResponse sendFeedback(TutorProfileForm tutorProfileForm);

}
