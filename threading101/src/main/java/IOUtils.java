import java.io.*;

public class IOUtils {
    public static void copy(InputStream src, OutputStream desc) throws IOException {
        int value;
        while ((value = src.read()) != -1) {
            desc.write(value);

        }
         src.close();
        desc.close();
    }

    public static void copyFile(String src, String desc) throws IOException{
        FileInputStream input = new FileInputStream(src);
        FileOutputStream output = new FileOutputStream(desc);

        copy(input, output);
    }
}
