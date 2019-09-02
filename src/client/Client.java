package client;

import request.Message;
import request.RequestType;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    //Constants
    private static final int PORT = 16500;

    //Class variables
    private String hostIP = "192.168.0.108";
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ClientListener listener;

    //Constructor
    public Client(ClientListener listener) {
        this.listener = listener;
    }

    //Run method that runs on a thread
    @Override
    public void run() {
        try {
            //Trying to connect to server
            Socket socket = new Socket(hostIP, PORT);

            //Initializing input and outputstreams to communicate with server
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            //Endless loop that updates the textArea in the gui with message from the server
            Message message;
            while (true){
                message = (Message) ois.readObject();
                listener.updateUi(message.getSender() + ":" + message.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void messageToServer(String message){
        try {
            //Writes a new Message object to the server
            oos.writeObject(new Message("Nicklas", message, RequestType.MESSAGE));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
