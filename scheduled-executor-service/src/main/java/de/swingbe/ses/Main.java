package de.swingbe.ses;

import java.util.concurrent.*;
import java.util.*;

//System.out.println();
import java.io.*;
  
class SchedulerExecutorServiceExample {
    
    public static void main(String[] args)
    {
        System.out.println(
            "A count-down-clock program that counts from 10 to 0");
  
        // creating a ScheduledExecutorService object
        ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(11);
  
        // printing the current time
        System.out.println(
            "Current time : "
            + Calendar.getInstance().get(Calendar.SECOND));
  
        // Scheduling the tasks
        for (int i = 10; i >= 0; i--) {
            scheduler.schedule(new Task(i), 10 - i,
                               TimeUnit.SECONDS);
        }
  
        // remember to shutdown the scheduler
        // so that it no longer accepts
          // any new tasks
        scheduler.shutdown();
    }
}
  
class Task implements Runnable {
    private int num;
    public Task(int num) { this.num = num; }
    public void run()
    {
	if(num%1==0){
	    // create instance of Random class
	    Random rand = new Random();
	    // Generate random integers in range 0 to 1999
	    int randInt = rand.nextInt(2000);
	    
	    System.out.println("num: " +num+", rand: " + randInt);
	    try {
		Thread.sleep(randInt);
            } catch (InterruptedException e) {
                System.out.println("Another thread has interrupted this one, message: " + e.getMessage() + ", trace: " + e.getStackTrace());
            }
	    System.out.println("num: "+num+", sleep() done.");
	}
        System.out.println(
            "num: " + num + " Current time : "
            + Calendar.getInstance().get(Calendar.SECOND));
    }
}
