package de.swingbe.line_no_count;

import java.io.*;

public class LineNoCounter {

    public static void count() {
        File file = new File("/opt/npm/NetPeerManager.log");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
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

    }
}
