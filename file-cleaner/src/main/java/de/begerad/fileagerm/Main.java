package de.begerad.fileagerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

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
            System.out.println("dir path: "
                    + (new File(dirPath).getCanonicalPath()));
            LOG.debug("dir path: {}",
                    (new File(dirPath).getCanonicalPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileExplorer fileList = new FileExplorer(dirPath);
        File[] list = fileList.listFilesUsingFileFilter();
        LOG.debug("List of files in the specified directory:");
        if (list != null) {
            for (File file : list) {
                LOG.debug("file name: {}", file.getName());
                LOG.debug("file path: {}", file.getPath());
                LOG.debug("file abs path: {}", file.getAbsolutePath());
                try {
                    LOG.debug("file can path: {}", file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        deleteFilesByAge(age, dirPath);

        LOG.debug("main() done.");
    }
}
