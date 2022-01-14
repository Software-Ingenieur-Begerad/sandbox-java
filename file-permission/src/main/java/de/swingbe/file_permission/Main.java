package de.swingbe.file_permission;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("main started...");

        // creating a file instance
        File file = new File("/opt/npm/NetPeerManager.log");

        // check if the file exists
        boolean exists = file.exists();
        if (exists) {
            // printing the permissions associated with the file
            System.out.println("Executable: " + file.canExecute());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
        } else {
            System.out.println("File not found.");
        }
    }
}
