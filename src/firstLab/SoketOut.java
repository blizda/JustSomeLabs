package firstLab;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class SoketOut extends Thread {
    private String line = null;
    private Socket socket;
    private BufferedReader keyboard;
    public SoketOut (Socket socket, BufferedReader keyboard) {
        this.socket = socket;
        this.keyboard = keyboard;
    }
    public void run() {
        OutputStream sout = null;
        try {
            sout = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream out = new DataOutputStream(sout);
        while (true) {
            try {
                line = keyboard.readLine();
                out.writeUTF(line);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}