/*
 * File: EmailUtil.java
 * Author: Nan Jiang
 * Modified By: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class EmailUtil {

    private static EmailUtil emailUtil;

    @Autowired
    private ConfigurableSettings configurableSettingsAutowired;
    private static ConfigurableSettings configurableSettings;


    @PostConstruct
    public void init() {
        configurableSettings = this.configurableSettingsAutowired;
        EMAIL = configurableSettings.getEmailAddress();
        PASSWORD = configurableSettings.getEmailPassword();
        ADMIN_EMAIL = configurableSettings.getAdminEmailAddress();
        EXECUTOR_SERVICE= Executors.newFixedThreadPool(configurableSettings.getEmailThreadPoolSize());
        AUTH = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        };
        PROPERTIES = new Properties();
        PROPERTIES.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        PROPERTIES.put("mail.smtp.port", "587"); //TLS Port
        PROPERTIES.put("mail.smtp.auth", "true"); //enable authentication
        PROPERTIES.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
    }

    private static String EMAIL;
    private static String ADMIN_EMAIL;
    private static String PASSWORD;
    private static Properties PROPERTIES;
    private static ExecutorService EXECUTOR_SERVICE;
    private static Authenticator AUTH;
    private static final String REGISTRATION_SUBJECT = "Confirmation of signup - A home to share - DoNotReply";
    private static final String BOOKING_REQUEST_SUBJECT = "Renter Booking Request - A home to share - DoNotReply";
    private static final String RESET_PASSWORD_SUBJECT = "Reset password - A home to share - DoNotReply";

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    private EmailUtil(){

    }


    public static EmailUtil getInstance(){
        if(null == emailUtil){
            emailUtil = new EmailUtil();
        }
        return emailUtil;
    }


    /**
     * Utility method to send confirmation email
     * @param toEmail
     */
    public void sendRegistrationEmail(String toEmail, String fullname){

        Runnable sendRegistrationEmail = new Runnable() {
            @Override
            public void run() {
                try
                {
                    Session session = Session.getInstance(PROPERTIES, AUTH);
                    MimeMessage msg = new MimeMessage(session);
                    //set message headers
                    initMessage(msg);
                    msg.setFrom(new InternetAddress(EMAIL, "NoReply - A Home To Share"));
                    msg.setSubject(REGISTRATION_SUBJECT, "UTF-8");
                    msg.setText(registrationContent(fullname), "UTF-8");
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                    Transport.send(msg);
                }
                catch (Exception e) {
                    logger.error("Failed to send Registration Email", e);
                }
            }
        };
        EXECUTOR_SERVICE.execute(sendRegistrationEmail);
    }

    public void sendBookingRequestEmail(String bookRequestContent){
        Runnable sendBookingRequestEmail = new Runnable() {
            @Override
            public void run() {
                try
                {
                    Session session = Session.getInstance(PROPERTIES, AUTH);
                    MimeMessage msg = new MimeMessage(session);
                    //set message headers
                    initMessage(msg);
                    msg.setFrom(new InternetAddress(EMAIL, "A Home To Share"));
                    msg.setSubject(BOOKING_REQUEST_SUBJECT, "UTF-8");
                    msg.setText(bookRequestContent, "UTF-8");
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ADMIN_EMAIL, false));
                    Transport.send(msg);
                }
                catch (Exception e) {
                    logger.error("Failed to send Book Request Email", e);
                }
            }
        };
        EXECUTOR_SERVICE.execute(sendBookingRequestEmail);
    }

    public void sendResetPasswordEmail(String toEmail, String newPassword){
        Runnable sendBookingRequestEmail = new Runnable() {
            @Override
            public void run() {
                try
                {
                    Session session = Session.getInstance(PROPERTIES, AUTH);
                    MimeMessage msg = new MimeMessage(session);
                    initMessage(msg);
                    msg.setFrom(new InternetAddress(EMAIL, "A Home To Share -- Reset Password"));
                    msg.setSubject(RESET_PASSWORD_SUBJECT, "UTF-8");
                    msg.setText(resetPasswordEmailContent(newPassword), "UTF-8");
                    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                    Transport.send(msg);
                }
                catch (Exception e) {
                    logger.error("Failed to send Reset Password Email", e);
                }
            }
        };
        EXECUTOR_SERVICE.execute(sendBookingRequestEmail);
    }


    private String resetPasswordEmailContent(String newPassword){
        String resetPasswordEmailContent = "Your password at  A Home To Share has been reset, Please use this new password to login: \n\n"+newPassword+"\n\n"
                + "You can now use your new password to login. If you would like to change this password:\n"
                + "1. After logging in, click the 'My Account' tab in the top right hand corner.\n"
                + "2. Click 'Account Settings' in the side bar menu on the left.\n"
                + "3. For the field 'Old Password', enter the new password that we've provided above.\n"
                + "4. Enter in your desired password and confirm it in the text fields below.\n"
                + "5. Click the 'Change Password' button.\n\n"
                + "Sincerely,\n\n"
                + "'A Home to Share' Team";
        return resetPasswordEmailContent;
    }

    private String registrationContent(String fullname){
        String registrationContent = "Hello " + fullname + ",\n\n"
                + "This is an email to confirm that you have successfully registered with A Home to Share."
                + "You can now login to start using our website. We hope that you find everything that you're looking for!\n\n"
                + "If you have any questions, be sure to visit our F.A.Q. page, or click on \"How we work\".\n\n"
                + "Sincerely,\n\n"
                + "'A Home to Share' Team";
        return registrationContent;
    }

    private void initMessage(MimeMessage msg) throws Exception{
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setSentDate(new Date());
        msg.setReplyTo(InternetAddress.parse(EMAIL, false));
    }

}
