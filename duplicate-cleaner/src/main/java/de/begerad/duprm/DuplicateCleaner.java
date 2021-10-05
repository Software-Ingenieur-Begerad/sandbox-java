package de.begerad.duprm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DuplicateCleaner {

    public final static Logger LOG = LoggerFactory.getLogger(DuplicateCleaner.class);

    public static void clean(final String pathInput, final String pathOutput) {
        LOG.debug("clean() started.");

        String line;

        //instantiating the Scanner class
        Scanner sc;
        try {
            sc = new Scanner(new File(pathInput));
        } catch (FileNotFoundException e) {
            LOG.error("clean() ERROR while accessing " + pathInput + " file");
            LOG.error("clean() stack trace: " + e);
            return;
        }

        //instantiating the FileWriter class
        FileWriter writer;
        try {
            writer = new FileWriter(pathOutput);
        } catch (IOException e) {
            LOG.error("clean() ERROR while accessing " + pathOutput + " file");
            LOG.error("clean() stack trace: " + e);
            return;
        }

        //instantiating the Set class
        Set<String> set = new HashSet<>();

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (set.add(line)) {
                try {
                    writer.append(line).append(System.getProperty("line.separator"));
                } catch (IOException e) {
                    LOG.error("clean() ERROR while appending data");
                    LOG.error("clean() stack trace: " + e);
                    return;
                }
            }
        }
        try {
            writer.flush();
        } catch (IOException e) {
            LOG.error("clean() ERROR while flushing data");
            LOG.error(e.getMessage());
            e.printStackTrace();
            return;
        }

        LOG.debug("clean() done.");
    }
}
