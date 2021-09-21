package com.banger.backend.Service;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Service
public class emailService {


    private final String companyEmail = "timetabler4@gmail.com";
    private final String password = "timetabler@12345";
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
        prop.put("mail.smtp.host", Host);
        prop.put("mail.smtp.port", "587");
        prop.put(" mail.smtp.ssl.protocols","TLSv1.2");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        theAuthenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(companyEmail, password);
            }
        };

        session = Session.getDefaultInstance(prop, theAuthenticator);
    }

    public void EmailForRejectBooking(String recipientEmail){
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
                    "Please Check whether your Utility Bill and Other Details are upto date.<br/>" +
                    "If they are Upto-Date you can make bookings!<br/>" +
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



    public void  emailForBlackListUsers(String email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(companyEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("----------" +
                    "Oops !" +
                    "----------");

            String msg = "Dear Customer,<br/><br/>" +

                    "Your Account has been BlackListed due to a non collection of Booked Vehicle! <br/>" +
                    "Therefore, You will not be able to make bookings again in our Banger & Co Organization. <br/>" +

                    "Thank You!<br/>" +
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


    public void  EmailToNotifyUserInBookingAcceptance(String email) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(companyEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Booking Accepted!");

            String msg = "Dear Customer, <br/><br/>" +

                    "Your Booking has been Accepted by our Banger & Co Team! <br/>" +
                    "Now you can collect the Booked Vehicle from our Place. <br/><br/><br/>" +


                    "Thank You!<br/>" +
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


//    public void EmailToNotifyUserInBookingAcceptance(String Email) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(companyEmail));
//            message.setRecipients(
//                    Message.RecipientType.TO, InternetAddress.parse(Email));
//            message.setSubject("Booking Accepted!");
//
//            String msg = "Dear Customer, <br/><br/>" +
//
//                    "Your Booking has been Accepted by our Banger & Co Team! <br/>" +
//                    "Now you can collect the Booked Vehicle from our Place. <br/>" +
//
//                    "----------------------------------------------------------------<br/><br/>" +
//                    "Best Regards,<br/>" +
//                    "Banger & Co Team";
//            MimeBodyPart mimeBodyPart = new MimeBodyPart();
//            mimeBodyPart.setContent(msg, "text/html");
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(mimeBodyPart);
//            message.setContent(multipart);
//            Transport.send(message);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Error");
//        }
//    }

    public void sendEmailToDMV(String email, byte[] image, String licenseNumber){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(companyEmail));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Suspicious License Found!");

            String msg = "Hello, <br/><br/>" +
                    "Registration Number of Banger & Co with DMV : DMV085457 <br/>"+
                    "We have identified a person, who was trying to make his booking using a suspicious license number."+
                    "The booking was made at "+dtf.format(now)+
                    "Suspicious licence number is :"+licenseNumber+ "<br/>"+
                    "We have attached the image of that user with this email."+

                    "Thanking You,<br/>" +
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

}
