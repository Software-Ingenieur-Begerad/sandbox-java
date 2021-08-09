package de.begerad.fileagerm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileCleaner {
    private final static int AGE_YEAR = 365;
    private final static int AGE_HALF_YEAR = 183;
    private final static int AGE_ZERO = 0;
    private final static String DIR_PATH_TMP = "/tmp";
    private final static String DIR_PATH_VAR = "/var";
    private final static String DIR_PATH_DOT_NPM = "/root/.npm";
    private final static String DIR_PATH_TMP_DEL = "/tmp/del";

    @Test
    public void testZeroAge() {
        int count = FileCleaner.deleteFilesByAge(AGE_YEAR,
                DIR_PATH_TMP);
        assertEquals(0, count);
    }

    @Test
    public void testZeroFiles() {
        int count = FileCleaner.deleteFilesByAge(AGE_YEAR,
                DIR_PATH_VAR);
        assertEquals(0, count);
    }

    @Test
    public void testDirDoesNotExist() {
        int count = FileCleaner.deleteFilesByAge(AGE_YEAR,
                DIR_PATH_DOT_NPM);
        assertEquals(0, count);
    }

    @Test
    public void testRootDir() {
        int count = FileCleaner.deleteFilesByAge(AGE_HALF_YEAR,
                "/usr/local/share/fonts/");
        assertEquals(0, count);
    }

    @Test
    public void testFileDeleted() {
        int count = FileCleaner.deleteFilesByAge(AGE_ZERO,
                DIR_PATH_TMP_DEL);
        assertEquals(1, count);
    }
}
