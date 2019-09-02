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
    private ServerListener listener;

    //Constructor
    ClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos, ServerListener listener) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
        this.listener = listener;

    }

    //Run method that handles client actions
    @Override
    public void run() {

        Message request;


        while (true){

            try {

                System.out.println("Waiting for object from client");
                request = (Message) ois.readObject();

                System.out.println("request type = " + request.getType().toString());
                switch (request.getType()){
                    case MESSAGE:

                        System.out.println("Writing to other clients");
                        for(ClientHandler client : Server.clients){

                            listener.updateUI("Message sent from " + socket.getRemoteSocketAddress());
                            client.getOos().writeObject(request);
                            listener.updateUI("message delivered to " + client.getSocket().getRemoteSocketAddress().toString());

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
