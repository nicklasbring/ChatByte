package client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientGui implements ClientListener {

    public TextField tf_message;
    public TextArea ta_client_text;

    Client client = new Client(this);

    public void startPrivateChat(ActionEvent actionEvent) {
        Thread thread = new Thread(client);
        thread.start();
    }

    public void addRoom(ActionEvent actionEvent) {
    }

    public void addFriend(ActionEvent actionEvent) {
    }

    public void writeMessage(ActionEvent actionEvent) {
        client.messageToServer(tf_message.getText());
        tf_message.clear();
    }

    @Override
    public void updateUi(String string) {
        ta_client_text.appendText(string + "\n");
    }
}
