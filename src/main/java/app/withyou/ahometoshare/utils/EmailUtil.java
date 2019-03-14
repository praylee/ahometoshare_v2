package app.withyou.ahometoshare.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    /**
     * Utility method to send confirmation email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public static void sendEmail(String toEmail){

        final String fromEmail = "info.ahometoshare@gmail.com"; 
        final String password = "ahometoshare"; 
        final String subject = "Confirmation of signup - DoNotReply";
        final String body = "Thank you for signing up with A Home To Share!";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        
        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        try
        {
          MimeMessage msg = new MimeMessage(session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");

          msg.setFrom(new InternetAddress("info.ahometoshare@gmail.com", "NoReply - A Home To Share"));

          msg.setReplyTo(InternetAddress.parse("info.ahometoshare@gmail.com", false));

          msg.setSubject(subject, "UTF-8");

          msg.setText(body, "UTF-8");

          msg.setSentDate(new Date());

          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

          Transport.send(msg);  

        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
}