package firstLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRuner {

    public static void main(String[] args) {
        int port = 1488;
        Integer numOfCon = 0;
        try {
            ServerSocket ss = new ServerSocket(port);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            SinglString stringHolder = new SinglString();
            new ReadFromKeyboard(keyboard, stringHolder).start();
            while (true) {
                Socket socket = ss.accept();
                PassCheker pc = new PassCheker(numOfCon);
                new ServerSoketIn(socket, numOfCon, stringHolder, pc).start();
                new ServerSoketOut(socket, numOfCon, stringHolder, pc).start();
                numOfCon++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
