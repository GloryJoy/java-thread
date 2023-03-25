package filelist;

import utility.FileManager;

import java.io.File;
import java.util.*;
import java.util.concurrent.*;

public class FileListMain {
    public static void main(String[] args) {
        String pattern = "course";
        File dir = new File("/Volumes/Joy/projects/java/thread/threading101/sample");
        File []files = dir.listFiles();

        PatternFinder patternFinder = new PatternFinder();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long startTime = System.currentTimeMillis();
        for (File file: files){
            Future<List<Integer>> future = executorService.submit(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    List<Integer> listNumbers = patternFinder.find(file, pattern);
                    return listNumbers;
                }
            });

            resultMap.put(file.getName(), future);
        }

        waitForAll(resultMap);

        for (Map.Entry<String, Object> entry: resultMap.entrySet()){
            System.out.printf(
                    "%s found at line number - %s in file %s\n", pattern, entry.getValue(), entry.getKey()
            );
        }
        System.out.println("Time taken for search - " + (System.currentTimeMillis() - startTime));
    }

    private static void waitForAll(Map<String, Object> resultMap) {
        Set<String> keys = resultMap.keySet();

        for (String key: keys){
            Future<List<Integer>> future = (Future<List<Integer>>) resultMap.get(key);

            while (!future.isDone()){
                Thread.yield();
            }
            try {
                resultMap.put(key, future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
