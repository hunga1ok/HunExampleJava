package corp.hun.hunexamplejava;

/**
 * Created by Le Van Hung at 3/22/2022
 * at 15.54
 * HunExampleJava
 */

public class MultithreadingDemo extends Thread {
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
