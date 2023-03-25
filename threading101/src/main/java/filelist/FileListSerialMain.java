package filelist;

import java.io.File;
import java.util.List;

public class FileListSerialMain {
    public static void main(String[] args) {
        String pattern = "course";
        File dir = new File("/Volumes/Joy/projects/java/thread/threading101/sample");
        File []files = dir.listFiles();
        PatternFinder patternFinder = new PatternFinder();
        long startTime = System.currentTimeMillis();

        for (File file: files){
            List<Integer> lineNumbers = patternFinder.find(file, pattern);
            if (!lineNumbers.isEmpty()){
                System.out.printf("%s found at line number %s in the file - %s\n", pattern, lineNumbers, file.getName());
            }
        }

        System.out.println("Time taken for search is " + (System.currentTimeMillis() - startTime));
    }
}
