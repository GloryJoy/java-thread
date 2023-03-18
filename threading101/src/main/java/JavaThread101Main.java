import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.print("T");
        }
    }

}

class MyTask implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.print("~");

        }
    }
}

class CopyFileTask implements Runnable {

    private String srcFile;
    private String descFile;

    public CopyFileTask(String sourceFile, String destFile) {
        this.srcFile = sourceFile;
        this.descFile = destFile;
    }

    @Override
    public void run() {

        try {
            IOUtils.copyFile(srcFile, descFile);
            System.out.println(String.format("File copy of %s to %s is done.", srcFile, descFile));
        } catch (IOException e) {
            System.out.println(String.format("Error occurred during file copy: %s", e.getMessage()));
        }

    }
}

public class JavaThread101Main {
    public static void main(String[] args) {
//        MyThread myThread = new MyThread();
//        myThread.start();
//
//        MyTask myTask = new MyTask();
//        Thread thread2 = new Thread(myTask);
//        thread2.start();
        final int THRED_POOL_SIZE = 5;
        final String sourceFile1 = "a.txt";
        final String destFile1 = "b.txt";
        final String sourceFile2 = "c.txt";
        final String destFile2 = "d.txt";


        ExecutorService executorService = Executors.newFixedThreadPool(THRED_POOL_SIZE);
        executorService.execute(new CopyFileTask(sourceFile1, destFile1));
        executorService.execute(new CopyFileTask(sourceFile2, destFile2));


    }
}
