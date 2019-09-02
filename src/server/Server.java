package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    //Constant
    private static final int PORT = 16500;

    //Klassevariabler
    private ServerSocket server;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    //No arg constructor
    public Server() {

    }

    //Run metode som bliver overskrevet fra Runnable klassen
    //Den bliver brugt til at køre serveren i på en tråd
    @Override
    public void run() {
        try {

            //Initialiserer serveren på en angivet port
            server = new ServerSocket(PORT);
            System.out.println("Serveren er startet");//Skal smides i textarea

            while (true) {
                //Accepterer hvis en klient prøver at tilslutte til serveren
                socket = server.accept();

                //Initialiserer datainput- og dataoutputstream for at kunne kommunikere mellem server og klient
                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());


                //TODO: create client handler that handles clients on a thread

                //TODO: create a Vector or HashMap that keeps track of all connected clients (thread-safe!!!!)




            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
