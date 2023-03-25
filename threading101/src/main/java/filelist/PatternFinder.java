package filelist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatternFinder  {
    public List<Integer> find(File file, String pattern){
        List<Integer> lineNumbers = new ArrayList<Integer>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int lineNo = 1;
            String line = "";
            while (true){
                try {
                    line = bufferedReader.readLine();
                    if (line == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line.contains(pattern)){
                    lineNumbers.add(lineNo);
                }
                lineNo++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return lineNumbers;
    }
}
