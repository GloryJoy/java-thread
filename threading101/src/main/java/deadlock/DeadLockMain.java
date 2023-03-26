package deadlock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Sample {

    private int x;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incr() {
        Lock lock = readWriteLock.writeLock();

        lock.lock();

        try {
            int y = getX();
            y++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setX(y);

        } finally {
            lock.unlock();
        }

    }
}

class MyThread extends Thread {
    private Sample obj;

    public MyThread(Sample obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        obj.incr();
    }
}

public class DeadLockMain {
    public static void main(String[] args) {
        Sample sample = new Sample();
        sample.setX(10);

        var t1 = new MyThread(sample);
        var t2 = new MyThread(sample);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sample.getX());

    }
}
