package producer_consumer;


import java.util.ArrayList;
import java.util.List;

class MessageQueue {
    List<String> messages = new ArrayList<String>();
    int limit;

    public MessageQueue(int limit) {
        this.limit = limit;
    }

    public boolean isFull() {
        return messages.size() == limit;
    }

    public boolean isEmpty() {
        return messages.size() == 0;
    }

    public synchronized void enqueue(String msg) {
        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        messages.add(msg);
        this.notify();
    }

    public synchronized String dequeue() {
        String message = "";
        while (isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        message = messages.remove(0);
        this.notify();
        return message;
    }


}

class ProducerThread extends Thread {
    MessageQueue queue;

    public ProducerThread(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            String msg = "Hello-" + i;
            queue.enqueue(msg);
            System.out.printf("Produced - %s\n", msg);
        }
    }
}

class ConsumerThread extends Thread {
    MessageQueue queue;

    public ConsumerThread(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            String message = queue.dequeue();
            System.out.printf("Consumed - %s\n", message);
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(1);
        new ProducerThread(queue).start();
        new ConsumerThread(queue).start();

    }
}
