package server;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Date;

public class ServerGui implements ServerListener {


    @FXML TextArea ta_server_text;
    @FXML Label lb_client_count;
    @FXML Label lb_server_start_timestamp;
    @FXML JFXButton btn_start_server;
    @FXML Label label_startServerPromtText;

    public void initialize(){
        ta_server_text.wrapTextProperty().setValue(true);
    }

    //Start Server button onAction
    //Runs when start server button is pressed on server gui
    public void startServer(ActionEvent actionEvent) {

        Server server = new Server(this);

        Thread thread = new Thread(server);

        thread.start();

        btn_start_server.setDisable(true);
        label_startServerPromtText.setVisible(false);

    }

    @Override
    public void updateUI(String s) {

        Platform.runLater(() -> ta_server_text.appendText(s + "\n"));
    }

    @Override
    public void updateClientCount(int clientCount) {

        Platform.runLater(() -> lb_client_count.setText(String.valueOf(clientCount)));
    }

    @Override
    public void updateServerStatus() {
        String status = new Date().toString();

        Platform.runLater(() -> lb_server_start_timestamp.setText("Serveren er startet: " + status));
    }

}
