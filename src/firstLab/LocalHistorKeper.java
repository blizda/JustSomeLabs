package firstLab;


public class LocalHistorKeper {
    private static volatile boolean isAut = false;
    private static volatile String myLogin;
    public static void setLogin(String login){
        myLogin = login;
    }
    public static void setAut(){
        isAut = true;
    }
    public static boolean chekAut(){
        return isAut;
    }
    public static String getLogin(){
        return myLogin;
    }
}
