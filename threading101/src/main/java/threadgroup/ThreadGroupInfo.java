package threadgroup;

class DemoTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class ThreadGroupInfo {
    public static void main(String[] args) {

        ThreadGroup demoThreadGroup = new ThreadGroup("demoThreadGroup");

//        Thread demoThread = new Thread(new DemoTask(), "DemoThread");
        Thread demoThread = new Thread(demoThreadGroup, new DemoTask(), "DemoTask in a Group");
        demoThread.setPriority(4);
        demoThread.start();
        System.out.println("---- system thread -----");
        Thread currentThread = Thread.currentThread();
        ThreadGroup threadGroup = currentThread.getThreadGroup();
        while (threadGroup.getParent() != null){
            threadGroup = threadGroup.getParent();
        }

        threadGroup.list();

        Thread [] threads = new Thread[10];
        int n = threadGroup.enumerate(threads);

        for (int i =0; i< n;i++){
            System.out.printf("Thread Name: %s \nisDemon: %s \n", threads[i].getName(), threads[i].isDaemon());
        }

    }
}
