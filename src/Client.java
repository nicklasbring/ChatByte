import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {

    private int portNumber;
    private String hostIP;
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;


    @Override
    public void run() {

        try {
            Socket socket = new Socket("hostIP", portNumber);

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
