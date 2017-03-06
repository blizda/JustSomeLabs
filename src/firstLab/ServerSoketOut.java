package firstLab;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

class ServerSoketOut extends Thread {
    private final Socket socket;
    private String line;
    private SinglString myString;
    private Integer numOfCon;
    private SoketWriteInFile file;
    private boolean isFerstCon = true;
    private PassCheker pc;
    public ServerSoketOut(Socket socket, Integer numOfCon, SinglString myString, PassCheker pc) {
        this.socket = socket;
        this.myString = myString;
        this.numOfCon = numOfCon;
        this.pc = pc;
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
                if (isFerstCon && pc.isAutorise()){
                    isFerstCon = false;
                    out.writeUTF(new SoketReadFile().getStreang().replace(NameHolder.getNameFromId(numOfCon) + ": ", ""));
                }
                else if((myString.getString() != null) && (!(myString.getString() == line) )) {
                    if ((!numOfCon.equals(myString.getNum()))) {
                        line = myString.getString();
                        out.writeUTF(NameHolder.getNameFromId(myString.getNum()) + ": " + line);
                        out.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
