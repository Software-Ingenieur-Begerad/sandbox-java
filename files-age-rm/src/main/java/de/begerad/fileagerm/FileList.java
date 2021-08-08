package de.begerad.fileagerm;

import java.io.File;
import java.io.FileFilter;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileList {
    private final String directory;

    public FileList(String directory) {
        this.directory = directory;
    }

    /**
     * @return set containing list of file paths
     */
    public Set<String> listFilesUsingJavaIO() {
        return Stream.of(new File(directory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    /**
     * @return array containing list of File objects
     */
    public File[] listFilesUsingFileFilter() {
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
