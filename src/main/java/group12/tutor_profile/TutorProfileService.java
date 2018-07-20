package group12.tutor_profile;

import group12.DatabaseInterface;
import group12.registration.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class TutorProfileService implements iTutorProfile {

    private DatabaseInterface db;
    private static Logger logger = LogManager.getLogger(RegistrationService.class);

    @Value("${server.url}")
    String serverURL;

    public void setDb(DatabaseInterface db) {
        this.db = db;
    }

    @Override
    public TutorProfileResponse getTutorProfile(TutorProfileForm tutorProfileForm) {

        return null;
    }
}
