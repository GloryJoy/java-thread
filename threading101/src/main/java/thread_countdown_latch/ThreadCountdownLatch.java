package thread_countdown_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UserMigrateTask implements Runnable{
    @Override
    public void run() {
        int recordCount = 220;
        int batchSize = 100;
        int nPages = (int) Math.ceil((double) recordCount/batchSize);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(nPages);

        for (int i = 1; i<=nPages; i++){
            final int pageNo = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("Migrating page - %d\n", pageNo);
                    latch.countDown();
                }
            });

        }

        executor.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("User data migration completed.");

    }
}
public class ThreadCountdownLatch {
    public static void main(String[] args) {
        Thread t1 = new Thread(new UserMigrateTask());
        t1.start();

    }
}
