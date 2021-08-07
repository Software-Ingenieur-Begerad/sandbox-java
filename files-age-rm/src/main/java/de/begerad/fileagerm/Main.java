package de.begerad.fileagerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

public class Main {
    public static String dirPath = "";

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void deleteFilesOlderThanNdays(int daysBack, String dirWay) {

        File directory = new File(dirWay);
        if (directory.exists()) {

            File[] listFiles = directory.listFiles();
            long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
            for (File listFile : listFiles) {
                if (listFile.lastModified() < purgeTime) {
                    if (!listFile.delete()) {
                        System.err.println("Unable to delete file: " + listFile);
                        Main.LOG.error("file could NOT be deleted: {}", listFiles);
                    } else {

                    }
                } else {
                    Main.LOG.debug("file is too young to be deleted");
                }
            }
        } else {
            Main.LOG.warn("directory does not exist");
        }
    }

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
            System.out.println("dir path: "
                    + (new File(dirPath).getCanonicalPath()));
            LOG.debug("dir path: {}",
                    (new File(dirPath).getCanonicalPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileList fileList = new FileList(dirPath);
        File[] list = fileList.listFilesUsingFileFilter();
        LOG.debug("List of files in the specified directory:");
        if (list != null) {
            for (File fileName : list) {
                LOG.debug("{}", fileName);
            }
        } else {
            LOG.warn("List of files is empty");
        }

        Set<String> setList = fileList.listFilesUsingJavaIO();
        LOG.debug("List of files in the specified directory:");
        for (String file : setList) {
            LOG.debug("{}", file);
        }

        int age = 1;
        long purgeTime = System.currentTimeMillis() - (age * 24L * 60L * 60L * 1000L);
        LOG.debug("age in ms: {}", purgeTime);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, age * -1);
        purgeTime = cal.getTimeInMillis();
        LOG.debug("age in ms: {}", purgeTime);

        deleteFilesOlderThanNdays(age, dirPath);

        LOG.debug("main() done.");
    }
}
