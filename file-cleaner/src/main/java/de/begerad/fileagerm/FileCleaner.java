package de.begerad.fileagerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileCleaner {

    public final static Logger LOG = LoggerFactory.getLogger(FileCleaner.class);

    /**
     * Clean-up files older than a certain time
     *
     * @param age     age of files to be cleaned up
     * @param dirPath directory path of files to be cleaned up
     */
    public static int deleteFilesByAge(int age, String dirPath) {

        int count = 0;
        LOG.debug("dirPath: {}", dirPath);
        File directory = new File(dirPath);
        if (directory.exists()) {

            File[] listFiles = FileExplorer.listFiles(dirPath);
            LOG.debug("listFiles.length: {}", listFiles.length);
            if (listFiles.length > 0) {
                long purgeTime = System.currentTimeMillis()
                        - (age * 24L * 60L * 60L * 1000L);
                for (File listFile : listFiles) {
                    if (listFile.lastModified() < purgeTime) {
                        if (!listFile.delete()) {
                            LOG.warn("file could NOT be deleted: {}",
                                    listFile.getName());
                        } else {
                            LOG.debug("file deleted: {}",
                                    listFile.getName());
                            count++;
                        }
                    } else {
                        LOG.debug("file is too young to be deleted: {}",
                                listFile.getName());
                    }
                }
            } else {
                LOG.debug("NO files present");
            }
        } else {
            LOG.warn("directory does not exist: {}", directory);
        }
        return count;
    }
}
