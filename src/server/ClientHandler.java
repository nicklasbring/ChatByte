package server;

import request.Request;
import request.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {

    //Instance variables
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private final Socket socket;
    private ServerListener listener;

    //Constructor
    ClientHandler(Socket socket, ServerListener listener) {
        this.socket = socket;
        this.listener = listener;

    }

    //Run method that handles client requests
    @Override
    public void run() {

        //Initialiserer datainput- og dataoutputstream for at kunne kommunikere mellem server og klient
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Failed to create Clienthandler input/output");
            e.printStackTrace();
        }

        Request request = null;

        while (!socket.isClosed()){

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

                        Server.fileTransferRequest = true;
                        Server.request = request;

                        Request response = new Request("kom", "nu", RequestType.APPROVED);
                        oos.writeObject(response);
                        oos.flush();


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
