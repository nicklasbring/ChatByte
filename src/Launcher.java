import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent serverGui = FXMLLoader.load(getClass().getResource("server/server_gui.fxml"));
        primaryStage.setTitle("ServerGui");
        primaryStage.setScene(new Scene(serverGui, 500, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
