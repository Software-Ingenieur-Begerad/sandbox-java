package de.swingbe.pdfbox;

public class Main {

    public static void main(String[] args) {
        System.out.println("inside : " + Thread.currentThread().getName());

        if (args.length != 3) {
            System.err.println("usage: " + Main.class.getName() + " <output-file> <Message> <TTF-file>");
            System.exit(1);
        }

        String fileOutput = args[0];
        String msg = args[1];
        String ttfPath = args[2];

        System.out.println("creating Runnable...");
        Runnable runnable = new HelloWorld(fileOutput, msg);

        System.out.println("creating Thread...");
        Thread thread = new Thread(runnable);

        System.out.println("starting Thread...");
        thread.start();

        System.out.println("creating Runnable...");
        Runnable runnableTTF = new HelloWorldTTF("TTF" + fileOutput, msg, ttfPath);

        System.out.println("creating Thread...");
        Thread threadTTF = new Thread(runnableTTF);

        System.out.println("starting Thread...");
        threadTTF.start();

        return;
    }
}
