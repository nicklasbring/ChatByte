package client;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    //Constants
    private static final int PORT = 16500;

    //Class variables
    private String hostIP = "192.168.0.108";
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    @Override
    public void run() {

        try {
            //Trying to connect to server
            Socket socket = new Socket(hostIP, PORT);

            //Initializing input and outputstreams to communicate with server
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
