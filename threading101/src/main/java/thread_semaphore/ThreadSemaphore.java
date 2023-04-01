package thread_semaphore;

import java.util.concurrent.Semaphore;

class PrintThread extends Thread {
    int id;
    Semaphore semaphore;

    public PrintThread(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.printf("Printer %d is printing...\n", id);
            Thread.sleep(2000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

public class ThreadSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        new PrintThread(1, semaphore).start();
        new PrintThread(2, semaphore).start();
        new PrintThread(3, semaphore).start();
        new PrintThread(4, semaphore).start();
    }
}
