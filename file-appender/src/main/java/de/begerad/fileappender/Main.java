package de.begerad.fileappender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static de.begerad.fileappender.FileAppender.append;

//TODO import static de.begerad.fileagerm.FileCleaner.deleteFilesByAge;

public class Main {

    public static String appendPath = "";

    public static String content = "";

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("main() started...");

        //check user input for file path
        if (args.length < 1) {
            LOG.error("Please enter path to file as first parameter.");
            return;
        }
        appendPath = args[0];
        File appendedRecords = new File(appendPath);

        //check user input for content
        if (args.length < 2) {
            LOG.error("Please enter content as second parameter.");
            return;
        }
        content = args[1];

        append(appendedRecords, content);

        LOG.debug("main() done.");
    }
}
