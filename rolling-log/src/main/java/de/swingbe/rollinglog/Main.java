package de.swingbe.rollinglog;

import org.apache.log4j.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2000; i++) {
            logger.info("This is the " + i + ". time I say 'Hello World'.");
            Thread.sleep(1);
        }
    }
}
