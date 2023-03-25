package callable;

import java.util.concurrent.*;

class MyCallableTask implements Callable<Integer> {

    private int first;
    private int second;

    public MyCallableTask(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Integer call() throws Exception {
        return this.first + this.second;
    }
}

public class CallableMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new MyCallableTask(10, 20));

        for (int i = 0; i <= 10; i++) {
            System.out.println("Printing round " + i + "st");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (!future.isDone()) ;

        try {
            int result = future.get();
            System.out.printf("Result is %d\n", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
