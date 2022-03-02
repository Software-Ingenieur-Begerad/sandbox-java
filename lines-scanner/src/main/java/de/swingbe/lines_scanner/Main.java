package de.swingbe.lines_scanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static void usage() {
        System.err.println("usage: java Main file");
        System.exit(-1);
    }


    private static void lines_scanner(Path pathFile) {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(pathFile.toString());
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);
            int count = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
                count++;
            }
            System.out.println("count: " + count);
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // parse arguments
        if (args.length == 0 || args.length > 2) usage();

        int fileArg = 0;

        // get lines count
        Path pathFile = Paths.get(args[fileArg]);
        System.out.println("pathFile: " + pathFile);
        lines_scanner(pathFile);

        return;
    }
}
