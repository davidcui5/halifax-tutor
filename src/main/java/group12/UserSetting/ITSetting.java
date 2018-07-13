package group12.UserSetting;

public interface ITSetting {
    TSettingResponse changeEmail(ChangeEmailForm form, String oldemail);
    TSettingResponse changePwd(ChangePwdForm form, String useremail);
    TSettingResponse changePhone(ChangePhoneForm form, String useremail);
    TSettingResponse changeCard(ChangeCardForm form, String useremail);

}
