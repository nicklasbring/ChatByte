package server;

public interface ServerListener {

    void updateUI(String s);

    default void updateClientCount(int clientCount){
        //override method for use
    }

    default void updateServerStatus(){
        //override method for use
    }

}
