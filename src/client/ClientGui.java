package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import request.Request;
import request.RequestType;

import java.io.File;

public class ClientGui implements ClientListener {

    public TextField tf_message;
    public TextArea ta_client_text;
    public Label lb_username;

    private Client client = new Client(this);

    private File file;

    public void initialize(){
        ta_client_text.wrapTextProperty().setValue(true);

    }

    public void startPrivateChat(ActionEvent actionEvent) {
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
            client.requestToServer(new Request(lb_username.getText(), tf_message.getText(), RequestType.MESSAGE));
            tf_message.clear();
        }

    }

    @Override
    public void updateUi(String string) {

        Platform.runLater(() -> ta_client_text.appendText(string + "\n"));
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

    public void sendFile(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Window stage = source.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose file");
        file = fileChooser.showOpenDialog(stage);

        System.out.println("File location: " + file.getPath());

        if(file != null) {
            Request request = new Request(lb_username.getText(), tf_message.getText(), RequestType.FILE_TRANSFER_REQUEST);
            request.setFile(file);

            client.requestToServer(request);
        } else {
            updateUi("Ingen fil valgt");
        }


    }


}
