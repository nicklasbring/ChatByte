package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    //Instance variables
    private final DataInputStream dis;
    private final DataOutputStream dos;
    private Socket socket;

    //Constructor
    public ClientHandler(DataInputStream dis, DataOutputStream dos, Socket socket) {
        this.dis = dis;
        this.dos = dos;
        this.socket = socket;
    }

    //Run method that handles client actions
    @Override
    public void run() {

    }


}
