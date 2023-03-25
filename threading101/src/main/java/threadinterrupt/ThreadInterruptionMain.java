package threadinterrupt;

class InterruptedThread extends Thread{
    @Override
    public void run() {
        for (;;){
            System.out.print("T");
            if (interrupted()) {
                System.out.print("Thread is interrupted, therefore stopping");
                break;
            }
        }
    }
}
public class ThreadInterruptionMain {

    public static void main(String[] args) {

        InterruptedThread interruptedThread = new InterruptedThread();
        interruptedThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(String.format("Error occurred : %s", e.getMessage()));
        }

        interruptedThread.interrupt();

    }
}
