package client;

import javafx.event.ActionEvent;

public class ClientGui {

    public void startPrivateChat(ActionEvent actionEvent) {
        Client client = new Client();

        Thread thread = new Thread(client);
        thread.start();
    }

    public void addRoom(ActionEvent actionEvent) {
    }

    public void addFriend(ActionEvent actionEvent) {
    }

    public void writeMessage(ActionEvent actionEvent) {
    }
}
