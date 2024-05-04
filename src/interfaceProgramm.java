import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class interfaceProgramm extends Application {

    public  void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample/startWindow.fxml"));
        primaryStage.setTitle("");
        primaryStage.getIcons().add(new Image("sticker.png"));
        primaryStage.setScene(new Scene(root, 533, 342));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
