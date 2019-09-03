package server;

import request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

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

    //Run method that handles client requests
    @Override
    public void run() {

        Request request;

        while (true){

            try {

                request = (Request) ois.readObject();

                switch (request.getType()){
                    case MESSAGE:

                        for(ClientHandler client : Server.clients){

                            client.getOos().writeObject(request);
                            listener.updateUI("Message sent from: " + socket.getRemoteSocketAddress() + "\n" +
                                    "   \"" + request.getSender() + ": " + request.getMsg() + "\""  );

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
                    case FILE_TRANSFER_REQUEST:
                        break;
                    case CLOSE:
                        throw new SocketException();
                }


            } catch (SocketException e) {
                break;
            }catch (IOException e) {
                listener.updateUI("Failed to complete client request");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                listener.updateUI("Class cast error (Bad request)");
                e.printStackTrace();
            }
        }

        closeCon();

    }

    private void closeCon(){
        try {

            Server.clientCounter--;
            listener.updateClientCount(Server.clientCounter);

            Server.clients.remove(this);

            listener.updateUI(socket.getRemoteSocketAddress() + " disconnected from the server");

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
