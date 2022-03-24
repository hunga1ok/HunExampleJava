package corp.hun.hunexamplejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    // Maximum number of threads in thread pool
    static final int MAX_T = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestThread.runTest();
    }

    void demoMultiThread() {

        int n = 8; // Number of threads
        for (int i = 0; i < n; i++) {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();
        }
    }

    void demoMultiThread2() {
        int n = 8; // Number of threads
        for (int i = 0; i < n; i++) {
            Thread object = new Thread(new MultiThreadingDemo2());
            object.start();
        }
    }

    void runSynchronization() {
        Sender send = new Sender();
        ThreadedSend S1 = new ThreadedSend(" Hi ", send);
        ThreadedSend S2 = new ThreadedSend(" Bye ", send);

        // Start two threads of ThreadedSend type
        S1.start();
        S2.start();

        // wait for threads to end
        try {
            S1.join();
            S2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        } finally {
            System.out.println("Finish");
        }
    }

    void runTest() {
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();
    }
}