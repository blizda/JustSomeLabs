package firstLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRuner {

    public static void main(String[] args) {
        int port = 1488;
        try {
            ServerSocket ss = new ServerSocket(port);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            SinglString stringHolder = new SinglString();
            new ReadFromKeyboard(keyboard, stringHolder).start();
            while (true) {
                Socket socket = ss.accept();
                new ServerSoketIn(socket, stringHolder).start();
                new ServerSoketOut(socket, stringHolder).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
