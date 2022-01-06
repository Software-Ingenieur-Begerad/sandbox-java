package de.swingbe.rollinglog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2000; i++) {
            logger.trace("TRACE: This is the " + i + ". time I say 'Hello World'.");
            logger.info("INFO: This is the " + i + ". time I say 'Hello World'.");
            logger.debug("DEBUG: This is the " + i + ". time I say 'Hello World'.");
            logger.warn("WARN: This is the " + i + ". time I say 'Hello World'.");
            logger.error("ERROR: This is the " + i + ". time I say 'Hello World'.");
            Thread.sleep(5);
        }
    }
}
