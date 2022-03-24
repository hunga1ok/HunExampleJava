package corp.hun.hunexamplejava;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThread {
    public static void runTest() {
        ExecutorService executor = Executors.newCachedThreadPool();

        // Cast the object to its class type
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;

        //Stats before tasks execution
        showInfo(pool);

        executor.submit(new Task("Task 1"));
        executor.submit(new Task("Task 2"));
        executor.submit(new Task("Task 3"));
        executor.submit(new Task("Task 4"));

        //Stats after tasks execution
        showInfo(pool);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                executor.submit(new Task("Task 5"));
                executor.submit(new Task("Task 6"));
                showInfo(pool);
            }
        }, 5000L);

        Executors.newSingleThreadScheduledExecutor().schedule(() -> showInfo(pool), 10, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().schedule(() -> showInfo(pool), 70, TimeUnit.SECONDS);

    }

    private static void showInfo(ThreadPoolExecutor pool) {
        System.out.println("=====================================");
        System.out.println("Core threads: " + pool.getCorePoolSize());
        System.out.println("Largest executions: " + pool.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + pool.getPoolSize());
        System.out.println("Currently executing threads: " + pool.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount());
    }
}
