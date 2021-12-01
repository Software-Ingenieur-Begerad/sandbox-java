package de.swingbe.mon_dir;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;

public class Main {
    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final String FOLDER = "/opt/npm";

    public static void main(String[] args) throws IOException,
            InterruptedException {
        LOG.trace("app started...");

        Path folderPath = Paths.get(FOLDER);
        LOG.debug("folderPath: " + folderPath);

        //obtain WatchService instance using FileSystems class
        WatchService watchService = FileSystems.getDefault().newWatchService();

        //register a Path instance for events using a WatchService instance
        folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);

        boolean valid = true;
        do {
            WatchKey watchKey = watchService.take();

            for (WatchEvent event : watchKey.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    String fileName = event.context().toString();
                    LOG.debug("File created: " + fileName);
                } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
                    Path context = (Path) event.context();
                    String fileName = context.toString();
                    LOG.debug("File modified: " + fileName);
                } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
                    String fileName = event.context().toString();
                    LOG.debug("File deleted: " + fileName);
                } else {
                    LOG.warn("Event unknown");
                }
                valid = watchKey.reset();

            }
        }
        while (valid);

        LOG.trace("app done.");
    }
}
