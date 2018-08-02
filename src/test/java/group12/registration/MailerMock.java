package group12.registration;

import group12.email.IMailer;

public class MailerMock implements IMailer {
    @Override
    public void sendMail(String from, String to, String subject, String message) {
        //I don't really send mail.
    }
}