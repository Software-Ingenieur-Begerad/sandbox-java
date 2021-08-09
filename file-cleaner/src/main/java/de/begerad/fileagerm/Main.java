package de.begerad.fileagerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import static de.begerad.fileagerm.FileCleaner.deleteFilesByAge;

public class Main {
    public static String dirPath = "";

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.debug("main() started...");

        //check user input for directory
        if (args.length < 1) {
            System.out.println("Please enter directory as first parameter.");
            return;
        }

        dirPath = args[0];
        if (!(new File(dirPath).isDirectory())) {
            System.out.println("first parameter: "
                    + dirPath
                    + " is NOT a valid directory.");
            return;
        }

        try {
            LOG.debug("dir path: {}",
                    (new File(dirPath).getCanonicalPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int age = 1;
        long purgeTime = System.currentTimeMillis() - (age * 24L * 60L * 60L * 1000L);
        LOG.debug("age in ms: {}", purgeTime);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, age * -1);
        purgeTime = cal.getTimeInMillis();
        LOG.debug("age in ms: {}", purgeTime);

        int filesDeleted = deleteFilesByAge(age, dirPath);
        LOG.debug("# files deleted: " + filesDeleted);

        LOG.debug("main() done.");
    }
}
