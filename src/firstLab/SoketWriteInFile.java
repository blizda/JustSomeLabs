package firstLab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SoketWriteInFile{
    public SoketWriteInFile(String str) throws FileNotFoundException {
        str = str + "\n";
        try {
            Files.write(Paths.get("log.txt"), str.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
