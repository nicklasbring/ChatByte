package client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class ClientGui {

    public TextField tf_message;
    Client client = new Client();

    public void startPrivateChat(ActionEvent actionEvent) {
        Thread thread = new Thread(client);
        thread.start();
    }

    public void addRoom(ActionEvent actionEvent) {
    }

    public void addFriend(ActionEvent actionEvent) {
    }

    public void writeMessage(ActionEvent actionEvent) {
        client.messageToServer(tf_message.getText().toString());

    }
}
