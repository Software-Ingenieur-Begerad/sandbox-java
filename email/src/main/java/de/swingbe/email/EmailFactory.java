package de.swingbe.email;

/**
 * Factory for creating instance of {@link Email}
 */
public class EmailFactory {

    /**
     * Create an instance of {@link Email}
     *
     * @return the created instance
     */
    public static Email createEmail() {
        return new EmailImpl();
    }
}
