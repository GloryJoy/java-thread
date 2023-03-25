package callableanonymous;

import java.util.concurrent.*;

public class CallableAnonymous {

    public static void main(String[] args) {

        final int first = 10;
        final int second = 20;

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new MathAdd().Add(first, second);
            }
        });

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
class MyCallableTask implements Callable<Integer> {

    private int first;
    private int second;

    public MyCallableTask(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Integer call() throws Exception {
        return new MathAdd().Add(first, second);
    }
}

class MathAdd {
        public int Add(int f, int s){
        return f+s;
    }
}

