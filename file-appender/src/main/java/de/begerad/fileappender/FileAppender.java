package de.begerad.fileappender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileAppender {

    public final static Logger LOG = LoggerFactory.getLogger(FileAppender.class);

    /**
     * Append text line to file
     *
     * @param file    object representing appending target
     * @param content object representing content to be appended
     */
    public static void append(File file, String content) {
        LOG.debug("append() started...");

        // default - create and write
        // if file not exists, create and write
        // if file exists, truncate and write
			/*try (FileWriter fw = new FileWriter(file);
					 BufferedWriter bw = new BufferedWriter(fw)) {
					bw.write(content);
					bw.newLine();
			}*/

        // append mode
        // if file not exists, create and write
        // if file exists, append to the end of the file
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(content);
            bw.newLine();   // add new line, System.lineSeparator()

        } catch (Exception e) {
            LOG.error("ERROR while appending file " + file.getName() + " with content " + content);
            e.printStackTrace();
        }

        LOG.debug("append() done.");
    }
}