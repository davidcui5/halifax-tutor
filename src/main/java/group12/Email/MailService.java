package group12.Email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

//concrete implementation of IMail with Spring's MailSender
public class MailService implements IMail{
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) {
        //creating message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        //sending message
        mailSender.send(message);
    }
}