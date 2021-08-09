package de.begerad.fileagerm;

import java.io.File;
import java.io.FileFilter;

public class FileExplorer {

    /**
     * @return array containing list of File objects
     */
    public static File[] listFiles(String directory) {
        //Creating a File object for directory
        File dir = new File(directory);
        //Creating filter for directory files
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File dir) {
                return dir.isFile();
            }
        };

        return dir.listFiles(fileFilter);
    }
}
