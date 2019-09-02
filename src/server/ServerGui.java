package server;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class ServerGui implements ServerListener {


    public TextArea ta_server_text;

    //Start Server button onAction
    //Runs when start server button is pressed on server gui
    public void startServer(ActionEvent actionEvent) {

        Server server = new Server(this);

        Thread thread = new Thread(server);

        thread.start();

    }


    @Override
    public void updateUI(String s) {
        ta_server_text.appendText(s + "\n");
    }
}
