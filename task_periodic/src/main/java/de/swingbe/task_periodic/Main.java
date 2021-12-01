package de.swingbe.task_periodic;

import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        //instantiate Timer object
        Timer time = new Timer();

        //instantiate ScheduledTask class
        ScheduledTask st = new ScheduledTask();

        //create repetitively task for every 1 secs
        time.schedule(st, 0, 1000);

        //for demo only.
        for (int i = 0; i < 5; i++) {
            System.out.println("Execution in Main Thread...." + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Another thread has interrupted this one");
                e.printStackTrace();
            }
            if (i == 4) {
                System.out.println("Application Terminates");
                System.exit(0);
            }
        }
        return;
    }
}
