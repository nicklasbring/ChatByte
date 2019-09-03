package client;

import request.Request;
import request.RequestType;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements Runnable {

    //Constants
    private static final int PORT = 16500;

    //Class variables
    private String hostIP = "localhost";
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ClientListener listener;
    private Socket socket = null;

    //Constructor
    public Client(ClientListener listener) {
        this.listener = listener;
    }

    //Run method that runs on a thread
    @Override
    public void run() {
        if(socket == null){
            try {
                //Trying to connect to server
                socket = new Socket(hostIP, PORT);

                listener.updateUi("You are connected to the server");

                //Initializing input and outputstreams to communicate with server
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());

                //Endless loop that updates the textArea in the gui with request from the server
                Request request;
                while (true){
                    request = (Request) ois.readObject();
                    listener.updateUi(request.getSender() + ": " + request.getMsg());
                }
            } catch (ConnectException e){
                listener.updateUi("Failed to connect to server\n" +
                        "   Server message: " + e.getLocalizedMessage());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            listener.updateUi("You are already connected to the server");
        }

    }


    void requestToServer(String name, String message, RequestType type){
        try {
            //Writes a new Request object to the server
            oos.writeObject(new Request(name, message, type));
            oos.flush();
        } catch (NullPointerException e){
            listener.updateUi("Failed to sent message, try connecting to server");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
