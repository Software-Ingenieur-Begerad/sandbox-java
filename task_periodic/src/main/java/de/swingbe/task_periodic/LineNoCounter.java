package de.swingbe.task_periodic;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LineNoCounter {

    public static void count() {
        System.out.println("count started...");

        File file = null;
        try {
            file = new File("/opt/npm/NetPeerManager.log");

        } catch (NullPointerException e) {
            System.out.println("path name arg is null");
            e.printStackTrace();
        }

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = null;
        inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        int lineCount = 0;

        while (true) {
            try {
                if (!((line = bufferedReader.readLine()) != null)) break;
            } catch (IOException e) {
                System.out.println("I/O error occured");
                e.printStackTrace();
            }
            lineCount++;
        }

        System.out.println("lineCount = " + lineCount);
        System.out.println("count done.");

    }
}
