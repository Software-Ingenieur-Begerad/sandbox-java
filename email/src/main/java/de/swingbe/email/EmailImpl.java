package de.swingbe.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

public class EmailImpl implements Email {
    private final static Logger LOG = LoggerFactory.getLogger(EmailImpl.class);

    @Override
    public void send(String sender, String receiver, String subject, String content, final String user, final String key, String host, String contentType) {

        //TODO Java version 11 and later are working only with TLSv1.2 with this implementation
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            LOG.error("ERROR getting Instance TLS");
            e.printStackTrace();
        }
        try {
            context.init(null, null, null);
        } catch (KeyManagementException e) {
            LOG.error("ERROR initialising context");
            e.printStackTrace();
        }
        String[] supportedProtocols = context.getDefaultSSLParameters().getProtocols();
        LOG.debug("supportedProtocols: " + Arrays.toString(supportedProtocols));
        String sslProtocols = supportedProtocols[supportedProtocols.length - 1];
        LOG.debug("sslProtocols: " + sslProtocols);

        /**
         * configure the library with our email service provider's credentials
         * then, create a Session that is used in constructing a message for sending
         * the configuration is via a Java Properties object
         */

        Properties prop = new Properties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", user);
        prop.put("mail.smtp.password", key);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", host);
        prop.put("mail.smtp.ssl.protocols", sslProtocols);

        //create a session with username and key
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, key);
            }
        });

        //create a MimeMessage for sending
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sender));
        } catch (MessagingException e) {
            LOG.error("ERROR setting From");
            e.printStackTrace();
        }
        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        } catch (MessagingException e) {
            LOG.error("ERROR setting Recipient");
            e.printStackTrace();
        }
        try {
            message.setSubject(subject);
        } catch (MessagingException e) {
            LOG.error("ERROR setting Subject");
            e.printStackTrace();
        }

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        try {
            mimeBodyPart.setContent(content, contentType);
        } catch (MessagingException e) {
            LOG.error("ERROR setting Content");
            e.printStackTrace();
        }

        Multipart multipart = new MimeMultipart();
        try {
            multipart.addBodyPart(mimeBodyPart);
        } catch (MessagingException e) {
            LOG.error("ERROR adding Body");
            e.printStackTrace();
        }

        try {
            message.setContent(multipart);
        } catch (MessagingException e) {
            LOG.error("ERROR setting Content");
            e.printStackTrace();
        }

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error("ERROR sending message");
            e.printStackTrace();
        }
    }
}
