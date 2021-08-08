package de.begerad.fileagerm;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileExplorer {

    @Test
    public void testEmptyListFiles() {
        File[] files = FileExplorer.listFiles("/mnt");
        assertTrue(files.length == 0);
    }

    @Test
    public void testNullListFiles() {
        assertNull(FileExplorer.listFiles("/etc/hosts"));
    }
}
