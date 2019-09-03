package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import request.RequestType;

public class ClientGui implements ClientListener {

    public TextField tf_message;
    public TextArea ta_client_text;
    public Label lb_username;

    private Client client = new Client(this);

    public void initialize(){
        ta_client_text.wrapTextProperty().setValue(true);
    }

    public void startPrivateChat(ActionEvent actionEvent) {

        System.out.println("virker");

    }

    public void addRoom(ActionEvent actionEvent) {
    }

    public void addFriend(ActionEvent actionEvent) {
    }

    public void writeMessage(ActionEvent actionEvent) {
        if(lb_username.getText().isEmpty()){
            ta_client_text.appendText("You must have a username to send messages!\n");
        } else if(tf_message.getText().isEmpty()){
            ta_client_text.appendText("There's nothing to send\n");
        } else {
            client.requestToServer(lb_username.getText(), tf_message.getText(), RequestType.MESSAGE);
            tf_message.clear();
        }

    }

    @Override
    public void updateUi(String string) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ta_client_text.appendText(string + "\n");
            }
        });
    }


    public void connectToServer(ActionEvent actionEvent) {
        Thread thread = new Thread(client);
        thread.start();
    }

    public void changeName(MouseEvent mouseEvent) {

        TextInputDialog td = new TextInputDialog("Enter a name");
        td.showAndWait();

        if(td.getEditor().getText().isEmpty()){
            lb_username.setText("Anonymous");
        } else{
            lb_username.setText(td.getEditor().getText());
        }

    }

    public void sendMessage(ActionEvent actionEvent) {
        writeMessage(actionEvent);
    }
}
