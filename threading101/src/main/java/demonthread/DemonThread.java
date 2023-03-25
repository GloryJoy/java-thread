package demonthread;
class DemoTask implements Runnable{

    @Override
    public void run() {
        for (;;){
            System.out.print("D");
        }
    }
}
public class DemonThread {
    public static void main(String[] args) {
//        ThreadGroup demoThreadGroup = new ThreadGroup("demo");
        Thread demoThread = new Thread(new DemoTask(), "demon-thread");
        demoThread.setDaemon(true);
        demoThread.start();

        for (int i=0; i<=100; i++){
            System.out.printf("demo number %d\n", i);
        }


    }
}
