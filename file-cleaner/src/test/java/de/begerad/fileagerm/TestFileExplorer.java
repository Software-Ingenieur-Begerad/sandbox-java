package de.begerad.fileagerm;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileExplorer {

    @Test
    public void testEmptyListFiles() {
        File[] files = FileExplorer.listFiles("/mnt");
        assertEquals(0, files.length);
    }

    @Test
    public void testNullListFiles() {
        assertNull(FileExplorer.listFiles("/etc/hosts"));
    }

    @Test
    public void testLengthListFiles() {
        File[] files = FileExplorer.listFiles("/var/www/test/public_html");
        assertEquals(1, files.length);
    }
}
