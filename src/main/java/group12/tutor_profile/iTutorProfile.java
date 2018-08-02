package group12.tutor_profile;

public interface iTutorProfile {

    TutorProfileResponse getTutorProfile(int tutorId);

    TutorProfileResponse sendMessage(TutorProfileForm tutorProfileForm);

    TutorProfileResponse sendFeedback(TutorProfileForm tutorProfileForm);

}
