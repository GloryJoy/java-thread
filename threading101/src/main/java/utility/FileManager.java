package utility;

import java.io.File;

public class FileManager {
    public void fileListing(String pathName){
        File dir = new File(pathName);
        File  [] files = dir.listFiles();

        for (File file : files){
            System.out.println(file.getName());
        }
    }
}
