package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Date;

public class ServerGui implements ServerListener {


    @FXML public TextArea ta_server_text;
    @FXML public Label lb_client_count;
    @FXML public Label lb_server_start_timestamp;
    @FXML public Button btn_start_server;

    //Start Server button onAction
    //Runs when start server button is pressed on server gui
    public void startServer(ActionEvent actionEvent) {

        Server server = new Server(this);

        Thread thread = new Thread(server);

        thread.start();

        btn_start_server.setDisable(true);

    }


    @Override
    public void updateUI(String s) {
        ta_server_text.appendText(s + "\n");
    }

    @Override
    public void updateClientCount(int clientCount) {
        lb_client_count.setText(String.valueOf(clientCount));
    }

    @Override
    public void updateServerStatus() {
        String status = new Date().toString();
        lb_server_start_timestamp.setText(status);
    }
}
