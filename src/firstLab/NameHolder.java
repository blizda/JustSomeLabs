package firstLab;

import java.util.Map;
import java.util.TreeMap;

public class NameHolder {
    private volatile static Map<Integer, String> names = new TreeMap<Integer, String>();
    public static void addName(Integer id, String name){
        names.put(id, name);
    }
    public static String getNameFromId(Integer id){
        return names.get(id);
    }
}
