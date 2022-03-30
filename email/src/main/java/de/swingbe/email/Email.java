package de.swingbe.email;

/**
 * Send an email.
 */
public interface Email {
    /**
     * Send an email.
     *
     * @param sender    sender of the email
     * @param receiver      recipient of the email
     * @param subject subject of the email
     * @param content content of the email
     * @param host host used to send email
     * @param user user from which the email is sent
     * @param key key of the user
     */
    void send(String sender, String receiver, String subject, String content, String user, String key, String host, String contentType);
}
