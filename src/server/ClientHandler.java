package server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

 public class ClientHandler implements Runnable {

    //Instance variables
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private Socket socket;

    //Constructor
    ClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
    }

    //Run method that handles client actions
    @Override
    public void run() {







    }


}
