package server;


public class ChatRoom implements Runnable {


    private int clientCounter = 0;

    public ChatRoom() {


    }

    @Override
    public void run() {

    }


    public int getClientCounter() {
        return clientCounter;
    }

    public void setClientCounter(int clientCounter) {
        this.clientCounter = clientCounter;
    }
}
