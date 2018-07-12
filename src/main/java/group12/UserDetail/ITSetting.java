package group12.UserDetail;

import group12.Registration.RegistrationResponse;
import group12.Registration.Student;
import group12.Registration.Tutor;

public interface ITSetting {
    TSettingResponse changeemail(ChangeEmailForm form, String useremail);
}
