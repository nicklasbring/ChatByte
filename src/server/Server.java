package server;

import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

public class Server implements Runnable{

    //Constant port number
    private static final int PORT = 16500;

    //Instance variables
    private ServerSocket server;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerListener listener;

    //vector used because it's thread safe
    static Vector<ClientHandler> clients;
    static Vector<ChatRoom> rooms;

    //counts number of clients
    static int clientCounter = 0;

    //No arg constructor
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
            server = new ServerSocket(PORT);

            listener.updateUI("Server running");
//            listener.updateServerStatus();



            while (true) {
                listener.updateUI("Waiting for client to connect");
                //Accepterer hvis en klient prøver at tilslutte til serveren
                socket = server.accept();

                listener.updateUI("Client request recieved");

                //Initialiserer datainput- og dataoutputstream for at kunne kommunikere mellem server og klient
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());



                //Creating a client thread to handle client
                ClientHandler client = new ClientHandler(socket, input, output, listener);
                Thread thread = new Thread(client);

                //Adding client to vector
                clients.add(client);

                thread.start();

                listener.updateUI("Client successfully handled");


                //increments number of clients connected to server
                clientCounter++;
                //listener.updateClientCount(clientCounter);

            }

        } catch (IOException e) {
            listener.updateUI("Error connecting client to server");
            e.printStackTrace();
        }
    }
}
