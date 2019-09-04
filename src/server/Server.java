package server;

import globalsettings.GlobalSettings;
import request.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable{

    //Instance variables
    private ServerSocket server;
    private Socket socket;

    private ServerListener listener;

    //vector used because it's thread safe
    static Vector<ClientHandler> clients;
    static Vector<ChatRoom> rooms;

    //counts number of clients
    static int clientCounter = 0;
    static boolean fileTransferRequest = false;
    static Request request = null;

    //Constructor
    Server(ServerListener listener) {
        this.listener = listener;

        clients = new Vector<>();
        rooms = new Vector<>();

    }

    //Overriding run() method from Runnable interface
    //Handling client request on a thread
    @Override
    public void run() {
        try {

            //Initialiserer serveren på en angivet port
            server = new ServerSocket(GlobalSettings.HOST_PORT);

            listener.updateUI("Server running");
            listener.updateServerStatus();



            while (true) {
                listener.updateUI("Waiting for client to connect");
                //Accepterer hvis en klient prøver at tilslutte til serveren
                socket = server.accept();

                if(fileTransferRequest){
                    fileTransferConn(socket, request, listener);
                } else {
                    clientConn(socket, listener);
                }

            }

        } catch (IOException e) {
            listener.updateUI("Error connecting client to server");
            e.printStackTrace();
        }
    }

    private static void clientConn(Socket socket, ServerListener listener){
        //Creating a client thread to handle client
        ClientHandler client = new ClientHandler(socket, listener);
        Thread thread = new Thread(client);

        //Adding client to vector
        clients.add(client);

        thread.start();

        listener.updateUI("A new client connected");


        //increments number of clients connected to server
        clientCounter++;
        listener.updateClientCount(clientCounter);
    }

    private static void fileTransferConn(Socket socket, Request request, ServerListener listener){
        fileTransferRequest = false;
        new Thread(new FileTransferHandler(socket, request , listener)).start();
        request = null;
    }

}
