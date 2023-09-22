import java.io.*;

public class FileUtilities {
    public static String load(String path) throws IOException {
        File file = new File(path);

        if(!file.exists()) throw new RuntimeException();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder builder = new StringBuilder();
        reader.lines().forEach(builder::append);

        return builder.toString();
    }
}
