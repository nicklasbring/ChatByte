package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable {

    //Instance variables
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private final Socket socket;
    private Vector<ChatRoom> connectedRooms;

    //Constructor
    ClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;

        connectedRooms = new Vector<>();

    }

    //Run method that handles client actions
    @Override
    public void run() {


        while (true){



        }


    }

     public ObjectInputStream getOis() {
         return ois;
     }

     public ObjectOutputStream getOos() {
         return oos;
     }

     public Socket getSocket() {
         return socket;
     }
 }
