package server;

import request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

        Request request;


        while (true){

            try {

                System.out.println("Waiting for object from client");
                request = (Request) ois.readObject();

                System.out.println("request type = " + request.getType().toString());
                switch (request.getType()){
                    case MESSAGE:

                        System.out.println("Writing to other clients");
                        for(ClientHandler client : Server.clients){

                            client.getOos().writeObject(request);
                            listener.updateUI("Message sent from: " + socket.getRemoteSocketAddress() + "\n" +
                                    "   \"" + request.getSender() + " : " + request.getMsg() + "\""  );

                        }

                        break;
                    case NEW_ROOM_REQUEST:
                        break;
                    case ROOM_REQUEST:
                        break;
                    case PRIVATE_REQUEST:
                        break;
                    case LEAVE_ROOM:
                        break;
                    case CLOSE:
                        closeCon();
                        break;
                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                listener.updateUI("Something went wrong with a Client-request");
                e.printStackTrace();
            }
        }


    }

    private void closeCon(){
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
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
