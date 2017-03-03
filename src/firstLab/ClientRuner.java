package firstLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRuner {

    public static void main(String[] args) {
        int port = 1488;
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            final Socket socket = new Socket(ipAddress, port);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            new SoketOut(socket, keyboard).start();
            new SoketIn(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
