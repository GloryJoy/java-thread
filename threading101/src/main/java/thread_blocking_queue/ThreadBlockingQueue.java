package thread_blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;

class ProducerThread extends Thread{
    java.util.concurrent.BlockingQueue<String> queue;

    public ProducerThread(java.util.concurrent.BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1;i<=10; i++){
            String msg = "Hello-" + i;
            try {
                queue.put(msg);
                System.out.printf("Produced - %s\n", msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerThread extends Thread{
    java.util.concurrent.BlockingQueue<String> queue;

    public ConsumerThread(java.util.concurrent.BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=1; i<=10;i++){
            String msg = null;
            try {
                msg = queue.take();
                System.out.printf("Consumed - %s\n", msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadBlockingQueue {

    public static void main(String[] args) {
        java.util.concurrent.BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);

        new ProducerThread(queue).start();
        new ConsumerThread(queue).start();

    }
}
