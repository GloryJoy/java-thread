package thread_fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SearchTask extends RecursiveTask<Integer>{
    @Override
    protected Integer compute() {
        System.out.printf("Thread info %s\n", Thread.currentThread());
        int size = end - start + 1;
        if (size > 3){
            int mid = (end + start)/2;
            SearchTask task1 = new SearchTask(arr, start, mid, searchElement);
            SearchTask task2 = new SearchTask(arr, mid+1, end, searchElement);

            task1.fork();
            task2.fork();

            int result = task1.join() + task2.join();
            return  result;
        } else {
            return processSearch();
        }
    }

    private Integer processSearch() {
        int count = 0;
        for (int i = start; i<=end; i++){
            if (arr[i] == searchElement){
                count++;
            }
        }
        return count;
    }

    int arr[];
    int start, end;
    int searchElement;

    public SearchTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }
}

public class ThreadForkJoin {
    public static void main(String[] args) {
        int arr[] = {6, 2, 6, 4, 5, 6, 7, 8, 6, 10, 11, 6};
        int searchElement = 6;
        int start = 0;
        int end = arr.length - 1;

        ForkJoinPool pool = ForkJoinPool.commonPool();
        SearchTask task = new SearchTask(arr, start, end, searchElement);
        int result = pool.invoke(task);
        System.out.printf("%d found %d times", searchElement, result);

    }
}
