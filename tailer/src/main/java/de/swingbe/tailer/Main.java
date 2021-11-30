package de.swingbe.tailer;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {
    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    //delay between checks of the file for new content in milliseconds
    public static final int DELAY_MILLIS = 500;

    //set to true to tail from the end of the file, false to tail from the beginning of the file
    public static final boolean END = true;

    public static void main(String[] args) {
        LOG.trace("Entering application.");

        TailerListener listener = new MyListener();

        //create and use a Tailer with a Thread
        String fileName = "/opt/npm/NetPeerManager.log";
        //TODO What is the behaviour of the reOpen parameter?
        Tailer tailer = new Tailer(new File(fileName), listener, DELAY_MILLIS, END);
        tailer.run();

        Thread thread = new Thread(tailer);

        //optional
        thread.setDaemon(true);

        thread.start();

        LOG.trace("Exiting application.");
        return;
    }
}
