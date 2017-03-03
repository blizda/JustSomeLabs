package firstLab;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class ServerSoketIn extends Thread {
    private final Socket socket;
    private volatile String line;
    private SinglString myString;
    public ServerSoketIn(Socket socket, SinglString myString) {
        this.socket = socket;
        this.myString = myString;
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
                myString.setMyString(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);
        }
    }
}
