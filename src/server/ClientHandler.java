package server;

import request.Message;
import request.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable {

    //Instance variables
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private final Socket socket;

    //Constructor
    ClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;

    }

    //Run method that handles client actions
    @Override
    public void run() {

        Message request;


        while (true){

            try {
                request = (Message) ois.readObject();

                switch (request.getType()){
                    case MESSAGE:

                        for(ClientHandler client : Server.clients){

                            if(client != this){
                                client.getOos().writeObject(request);
                            }
                            
                        }

                        break;
                    case NEW_ROOM_REQUEST:
                        break;
                    case ROOM_REQUEST:
                        break;
                    case PRIVATE_REQUEST:
                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


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
