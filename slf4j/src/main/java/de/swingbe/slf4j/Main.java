package de.swingbe.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        LOG.debug("Debug Message Logged !!!");
        LOG.info("Info Message Logged !!!");
        LOG.error("Error Message Logged !!!",
                new NullPointerException("NullError"));

        String variable = "Hello John";
        LOG.debug("Printing variable value: {}", variable);


        LOG.trace("Entering application.");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            LOG.error("Didn't do it.");
        }
        LOG.trace("Exiting application.");

        for (int i = 0; i < 2000; i++) {
            LOG.trace("TRACE: This is the " + i + ". time I say 'Hello World'.");
            LOG.info("INFO: This is the " + i + ". time I say 'Hello World'.");
            LOG.debug("DEBUG: This is the " + i + ". time I say 'Hello World'.");
            LOG.warn("WARN: This is the " + i + ". time I say 'Hello World'.");
            LOG.error("ERROR: This is the " + i + ". time I say 'Hello World'.");
            Thread.sleep(20);
        }
        return;
    }
}
