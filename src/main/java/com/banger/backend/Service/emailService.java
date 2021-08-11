package com.banger.backend.Service;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class emailService {

    private final String companyEmail = "bangerco480@gmail.com";
    private final String password = "Ac1918@lms";
    private final String Host = "smtp.gmail.com";
    private Properties property;
    private Session session;
    private Authenticator theAuthenticator;
    Properties prop;

    private static emailService theEmail = new emailService();

    public emailService() {
        prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        theAuthenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(companyEmail, password);
            }
        };

        session = Session.getDefaultInstance(prop, theAuthenticator);
    }

    public void EmailForRejectBooking(String recipientEmail) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(companyEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("----------" +
                    "Oops !" +
                    "----------");

            String msg = "Dear Valuable Customer,<br/><br/>" +

                    "Your Booking has been Rejected by our Team!.<br/>" +
                    "Please Check whether your Utility and Other Details are upto date.<br/>" +
                    "If you are Upto-Date you can make bookings!<br/>" +
                    "If you want any clarification please contact your team.<br/><br/>" +
                    "+94112-5889874 <br/><br/><br/>" +

                    "----------------------------------------------------------------<br/><br/>" +
                    "Best Regards,<br/>" +
                    "Banger & Co Team";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }


    public void EmailToNotifyUserInBookingAcceptance(String adminEmail) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(companyEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(adminEmail));
            message.setSubject("----------" +
                    "Congratulations !" +
                    "----------");

            String msg = "Dear Customer,<br/><br/>" +

                    "Your Booking has been Accepted by our Banger & Co Team! <br/>" +
                    "Now you can collect the Booked Vehicle from our Place. <br/>" +

                    "----------------------------------------------------------------<br/><br/>" +
                    "Best Regards,<br/>" +
                    "Development Team";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }

}
