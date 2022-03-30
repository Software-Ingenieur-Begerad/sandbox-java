package de.swingbe.email;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    private final static Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * The mail host from which the emails are sent.
     */
    private final static String mailHost = "mail.vbn.de";

    /**
     * The mail user from which the emails are sent.
     */
    private final static String mailUser = "Tarifmatrix";

    /**
     * The mail address from which the emails are sent.
     */
    private final static String mailSender = "no_reply_tm@vbn.de";

    private final static String mailReceiver = "sbegerad@posteo.de";

    /**
     * The mail user password from which the emails are sent.
     */
    private static final String password;

    static {
        //TODO make parameter accessible using configuration
//            password = new String(Base64.decodeBase64((new BufferedReader(new FileReader("." + File.separator + "credentials" + File.separator + "email.txt"))).readLine().getBytes()));
        password = new String(Base64.decodeBase64("cHRlVjlsVTdrekoyZEYwMTlKNDM="));
    }

    public static void main(String[] args) {
        LOG.debug("Hello world!");
        EmailFactory.createEmail().send(mailSender, mailReceiver, "Subject", "This is my first email using JavaMailer", mailUser, password, mailHost, "text/html; charset=utf-8");
        LOG.debug("Bye!");
        return;
    }
}
