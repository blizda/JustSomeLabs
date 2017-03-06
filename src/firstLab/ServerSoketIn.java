package firstLab;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class ServerSoketIn extends Thread {
    private final Socket socket;
    private volatile String line;
    private SinglString myString;
    private Integer numOfCon;
    private boolean isFerstCon = true;
    private PassCheker pc;
    public ServerSoketIn(Socket socket, Integer numOfCon, SinglString myString, PassCheker pc) {
        this.socket = socket;
        this.myString = myString;
        this.pc = pc;
        this.numOfCon = numOfCon;
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
                if (isFerstCon){
                    if (pc.isPass(line)){
                        isFerstCon = false;
                    }
                }
                else if(pc.isAutorise()){
                    myString.setMyString(line, numOfCon);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
