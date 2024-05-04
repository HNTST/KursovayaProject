package sample;

import com.database.DBQuere.DBQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import methods.miniMethods;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class ControlerRegistrationWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonRegContinue;

    @FXML
    private Text textDannie;

    @FXML
    private TextField textFieldAdres;

    @FXML
    private DatePicker dateBrtihday;

    @FXML
    private TextField textFieldFIO;

    @FXML
    private TextField textFieldNumberPhone;

    @FXML
    private TextField textFieldPOL;

    @FXML
    private TextField textFieldRegLogin;

    @FXML
    private TextField textFieldRegPassword;

    @FXML
    private TextField textFieldRegPasswordClone;

    @FXML
    private Text textRegistration;



    @FXML
    void initialize() {
        buttonRegContinue.setOnAction(event -> {
            miniMethods miniMethods = new miniMethods();
            DBQuery dbQuery = new DBQuery();

            LocalDate localDateDateBithsday = dateBrtihday.getValue();



            String fulName = textFieldFIO.getText();
            String enteredGender = textFieldPOL.getText();
            String enteredNumberPhone = textFieldNumberPhone.getText();
            String enteredContactInfo = textFieldAdres.getText();
            String enteredLogin = textFieldRegLogin.getText();
            String enteredPassword = textFieldRegPassword.getText();
            String enteredPasswordClone = textFieldRegPasswordClone.getText();


            String[] FIO = miniMethods.FIOSplit(fulName);

            String checkedPhoneNumber = miniMethods.isPhoneNumberValid(enteredNumberPhone);
            String checkedPassword = miniMethods.CheckPasswordInReg(enteredPassword, enteredPasswordClone);
            String checkedLoginInDB = dbQuery.CheckLoginInBD(enteredLogin);
            String checkedGender = miniMethods.GendorInBd(enteredGender);



            if (FIO != null && checkedPassword != null && checkedLoginInDB != null && checkedGender != null && checkedPhoneNumber != null) {
                java.sql.Date enteredData = java.sql.Date.valueOf(localDateDateBithsday);

                dbQuery.addMember(FIO[0], FIO[1], FIO[2], enteredData, checkedPhoneNumber, enteredContactInfo, checkedGender, checkedLoginInDB);
                dbQuery.addLoginPassword(checkedLoginInDB, checkedPassword,fulName);
                showAlert("Успешно", "Вы зарегистрировались, войдите в аккаунт");
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
                Stage stage = (Stage) buttonRegContinue.getScene().getWindow(); // закрытие окна Старт
                stage.close();

            } else {
                showAlert("Ошибка", "Некорректные данные. Пожалуйста, проверьте введенные данные.");
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        ImageView icon = new ImageView(new Image("/sticker.png"));
        icon.setFitWidth(30); // Задаем ширину изображения
        icon.setFitHeight(30); // Задаем высоту изображения

        // Устанавливаем изображение в заголовок Alert
        alert.setGraphic(icon);
        alert.showAndWait();
    }
}
