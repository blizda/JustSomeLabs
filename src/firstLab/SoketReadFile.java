package firstLab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SoketReadFile {
    private File file = new File("log.txt");
    private String str = "";
    public SoketReadFile(){
    }
    public String getString() {
        try(Scanner in = new Scanner(file)) {
            while(in.hasNext()){
                str += in.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return str.trim();
    }
}
