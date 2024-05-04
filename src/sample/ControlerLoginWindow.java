package sample;

import com.database.DBQuere.DBQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControlerLoginWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonContinue;

    @FXML
    private TextField textFieldLogib;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Label textReg;


    @FXML
    void initialize() {
        buttonContinue.setOnAction(event -> {
            String enteredLogin = textFieldLogib.getText();
            String enteredPassword = textFieldPassword.getText();
            methods.myPerson.setLogin(enteredLogin);
            methods.myPerson.setPassword(enteredPassword);
            try {
                DBQuery dbQuery = new DBQuery();
                if (dbQuery.LoginPasswordDB(enteredLogin, enteredPassword)) {
                    Parent root = FXMLLoader.load(getClass().getResource("personalAccount.fxml"));
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("/sticker.png"));
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();

                } else {
                    showAlert("Ошибка", "Неверный пароль");
                }


            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage = (Stage) buttonContinue.getScene().getWindow(); // закрытие окна Старт
            stage.close();//
        });
    }

    private void showAlert(String title, String massage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(massage);

        ImageView icon = new ImageView(new Image("/sticker.png"));
        icon.setFitWidth(30); // Задаем ширину изображения
        icon.setFitHeight(30); // Задаем высоту изображения

        // Устанавливаем изображение в заголовок Alert
        alert.setGraphic(icon);

        alert.showAndWait();
    }

}
