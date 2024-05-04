package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControlerStart {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Button buttonCloseWindow;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonRegistr;

    @FXML
    private Label mainText;

    @FXML
    private Label mainText2;

    @FXML
    private Label textReg;

    @FXML
    private Button buttonForADM;


    //СДЕЛАТЬ ПАНЕЛЬ АДМИНА ДЛЯ ПРОСМОТРА ВСЕХ ЧЛЕНОВ КЛУБА + ДОБАВЛЕНИЕ НОВЫХ АБОНИИМЕНТОВ И КОРРЕКТИРОВКА СТАРЫХ
    //СДЕЛАТЬ ПАНЕЛЬ АДМИНА ДЛЯ ПРОСМОТРА ВСЕХ ЧЛЕНОВ КЛУБА + ДОБАВЛЕНИЕ НОВЫХ АБОНИИМЕНТОВ И КОРРЕКТИРОВКА СТАРЫХ
    //СДЕЛАТЬ ПАНЕЛЬ АДМИНА ДЛЯ ПРОСМОТРА ВСЕХ ЧЛЕНОВ КЛУБА + ДОБАВЛЕНИЕ НОВЫХ АБОНИИМЕНТОВ И КОРРЕКТИРОВКА СТАРЫХ
    //СДЕЛАТЬ ПАНЕЛЬ АДМИНА ДЛЯ ПРОСМОТРА ВСЕХ ЧЛЕНОВ КЛУБА + ДОБАВЛЕНИЕ НОВЫХ АБОНИИМЕНТОВ И КОРРЕКТИРОВКА СТАРЫХ

    @FXML
    void initialize() {
        buttonForADM.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("EditsInDB.fxml"));
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/sticker.png"));
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
        });

        buttonCloseWindow.setOnAction(event -> {
            Stage stage = (Stage) buttonCloseWindow.getScene().getWindow();
            stage.close();
        });

        buttonLogin.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/sticker.png"));
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
            Stage stage = (Stage) buttonLogin.getScene().getWindow(); // закрытие окна Старт
            stage.close();//
        });
        buttonRegistr.setOnAction(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("registrationWindow.fxml"));
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/sticker.png"));
                stage.setScene((new Scene(root)));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = (Stage) buttonLogin.getScene().getWindow(); // закрытие окна Старт
            stage.close();//
        });
    }

}
