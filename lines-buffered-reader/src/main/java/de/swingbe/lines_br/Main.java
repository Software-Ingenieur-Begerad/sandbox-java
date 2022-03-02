package de.swingbe.lines_br;

import java.io.*;

public class Main {

    static void usage() {
        System.err.println("usage: java Main file");
        System.exit(-1);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // parse arguments
        if (args.length == 0 || args.length > 2) usage();

        int fileArg = 0;

        // get lines count
        try {
            FileUtil fileUtil = new FileUtil(args[fileArg]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("No. of lines in file: " + FileUtil.getLineCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}

class FileUtil {
    static BufferedReader reader = null;

    public FileUtil(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        reader = new BufferedReader(input);
    }

    public static int getLineCount() throws IOException {
        int lineCount = 0;
        String data;
        while ((data = reader.readLine()) != null) {
            lineCount++;
        }
        return lineCount;
    }
}