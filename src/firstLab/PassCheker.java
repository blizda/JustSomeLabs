package firstLab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PassCheker {
    private File file = new File("pass.txt");
    private String str = "";
    private Integer id;
    private boolean isAut = false;
    public PassCheker(Integer id) {
        this.id = id;
    }
    public boolean isPass(String logAndPass) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                str = in.nextLine().trim();
                if (logAndPass.equals(str)){
                    NameHolder.addName(id, str.split(" ")[0]);
                    isAut = true;
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isAutorise(){
        if(isAut){
            return true;
        }
        else{
            return false;
        }
    }
}
