package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class DemoEmailSender
{
    public static void main( String[] args )
    {
        System.out.println("Preparing message to send....");
        String message = "Hello, this is the message for security check!";
        String subject = "Confirmation Mail";
        String to = "roshnimali847@gmail.com";
        String from = "roshnimali92@gmail.com";

        sendEmail(message, subject, to, from);
    }

    /**
     *
     * @param message
     * @param subject
     * @param to
     * @param from
     * This is responsible to send email.
     */
    private static void sendEmail(String message, String subject, String to, String from) {

        // Variable for gmail
        String host = "smtp.gmail.com";

        // Get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES: " +properties);

        // Setting important information to properties object
        // host setting
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: To get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("roshnimali92@gmail.com", "***");
            }
        });
        session.setDebug(true);

        //Step 2: Compose the message (text, attachment, multimedia...)
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Step 3: Sending mail through Transport class
            Transport.send(mimeMessage);
            System.out.println("Mail Send Successfully...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
