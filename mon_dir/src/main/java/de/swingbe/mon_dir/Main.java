package de.swingbe.mon_dir;

import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) throws IOException,
            InterruptedException {

        Path folder = Paths.get("./");

        //obtain WatchService instance using FileSystems class
        WatchService watchService = FileSystems.getDefault().newWatchService();

        //register a Path instance for events using a WatchService instance
        folder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        boolean valid = true;
        do {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    String fileName = event.context().toString();
                    System.out.println("File created:" + fileName);
                } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
                    String fileName = event.context().toString();
                    System.out.println("File modified:" + fileName);
                } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
                    String fileName = event.context().toString();
                    System.out.println("File deleted:" + fileName);
                } else {
                    System.out.println("Event unkown");
                }
                valid = watchKey.reset();

            }
        }
        while (valid);
    }
}