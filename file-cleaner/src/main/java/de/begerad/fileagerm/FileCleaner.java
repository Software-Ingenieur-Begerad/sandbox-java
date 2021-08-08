package de.begerad.fileagerm;

import java.io.File;

import static de.begerad.fileagerm.Main.LOG;

public class FileCleaner {

    /**
     * Clean-up files older than a certain time
     *
     * @param age     age of files to be cleaned up
     * @param dirPath directory path of files to be cleaned up
     */
    public static void deleteFilesByAge(int age, String dirPath) {

        File directory = new File(dirPath);
        if (directory.exists()) {

            File[] listFiles = FileExplorer.listFiles(dirPath);
            LOG.debug("listFiles.length: {}", listFiles.length);
            long purgeTime = System.currentTimeMillis() - (age * 24L * 60L * 60L * 1000L);
            for (File listFile : listFiles) {
                if (listFile.lastModified() < purgeTime) {
                    if (!listFile.delete()) {
                        LOG.warn("file could NOT be deleted: {}", listFile.getName());
                    } else {
                        LOG.debug("file deleted: {}", listFile.getName());
                    }
                } else {
                    LOG.debug("file is too young to be deleted: {}", listFile.getName());
                }
            }
        } else {
            LOG.warn("directory does not exist: {}", directory);
        }
    }
}
