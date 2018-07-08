package group12.email;

//Having this interface allows us to have different concrete implementation of classes that can sendMail
//S.O.L.I.D.
public interface IMailer {

    void sendMail(String from, String to, String subject, String message);

}