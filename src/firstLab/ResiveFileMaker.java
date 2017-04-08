package firstLab;

import java.io.File;

public class ResiveFileMaker {
    public static String makeFile(String name){
        File file = new File(name);
        int i = 1;
        while (file.exists() && file.isFile()) {
            System.out.println(name);
                if (name.contains("\\.")){
                    System.out.println(name);
                    System.out.println(name.split("\\.")[0]);
                    String[] splits = name.split("\\.");
                    name = splits[0] +"\\."+  "(" + i + ")" + splits[1];
                    file = new File(name);
                }
                else {
                    name = name + "("+ i + ")";
                    file = new File(name);
                }
        }
        return name;
    }
}
