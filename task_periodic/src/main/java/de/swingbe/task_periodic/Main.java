package de.swingbe.task_periodic;

import java.io.BufferedReader;

import static de.swingbe.task_periodic.LineNoCounter.count;

public class Main {

    public static BufferedReader bufferedReader = null;

    private static void doNothing() {
        System.out.println("doNothing");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Another thread has interrupted this one");
            e.printStackTrace();
        }
    }

    private static void justDoIt() {
        System.out.println("justDoIt started...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Another thread has interrupted this one");
            e.printStackTrace();
        }
        System.out.println("justDoIt done.");
        return;
    }

    private static void doWhileTrue() {
        System.out.println("doWhileTrue started...");
        while (true) {
            //TODO clean up justDoIt();
            doNothing();
        }
    }

    private static void doForI() {
        System.out.println("doForI started...");
        for (int i = 0; i < 5; i++) {
            System.out.println("Execution in Main Thread...." + i);
            justDoIt();
        }
        System.out.println("doForI done.");
        return;
    }

    public static void main(String[] args) {
        System.out.println("main started...");

        while (true) {
            count();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Another thread has interrupted this one");
                e.printStackTrace();
            }

        }

        /*TODO clean up
        //instantiate Timer object
        Timer time = new Timer();

        //instantiate ScheduledTask class
        ScheduledTask st = new ScheduledTask();

        //create repetitively task for every 1 secs
        time.schedule(st, 0, 1000);

        //for demo only.
        //TODO clean updoForI();
        doWhileTrue();
         */

    }
}
