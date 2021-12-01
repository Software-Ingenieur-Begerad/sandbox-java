package de.swingbe.task_periodic;

import java.util.Date;
import java.util.TimerTask;

import static de.swingbe.task_periodic.LineNoCounter.count;

public class ScheduledTask extends TimerTask {

    private static void showDate() {

        //to display current time
        Date now;

        //initialize date
        now = new Date();

        //display current time
        System.out.println("Time is :" + now);

    }

    private static void showLineNoCount() {
        System.out.println("showLineNoCount started...");
        count();
        System.out.println("showLineNoCount done.");
    }

    //do your task here
    public void run() {

        //TODO clean up showDate();

        showLineNoCount();
    }
}
