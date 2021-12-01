package de.swingbe.task_periodic;

import java.util.Date;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask {

    //to display current time
    Date now;

    //ddd your task here
    public void run() {

        //initialize date
        now = new Date();

        //display current time
        System.out.println("Time is :" + now);
    }
}
