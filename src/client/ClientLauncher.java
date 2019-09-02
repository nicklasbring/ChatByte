package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client/client_gui.fxml"));
        primaryStage.setTitle("Chatbyte");
        primaryStage.setScene(new Scene(root, 700, 560));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
