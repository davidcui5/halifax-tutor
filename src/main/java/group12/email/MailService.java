package group12.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

//concrete implementation of IMail with Spring's MailSender
public class MailService implements IMail{

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String message) {
        //creating mail
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        //sending message
        mailSender.send(mail);
    }
}