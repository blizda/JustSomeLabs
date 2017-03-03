package firstLab;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class ServerSoketOut extends Thread {
    private final Socket socket;
    private String line;
    private SinglString myString;
    public ServerSoketOut(Socket socket, SinglString myString) {
        this.socket = socket;
        this.myString = myString;
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
                if ((myString.getString() != null) && (!(myString.getString() == line))) {
                    line = myString.getString();
                    out.writeUTF(line);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
