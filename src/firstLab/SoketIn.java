package firstLab;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class SoketIn extends Thread {
    String line = null;
    private Socket socket;
    public SoketIn(Socket socket) {
        this.socket = socket;
    }
    public void run() {

        InputStream sin = null;
        try {
            sin = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataInputStream in = new DataInputStream(sin);
        while (true) {
            try {
                line = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);
        }
    }
}