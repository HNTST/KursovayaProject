package sample;

import com.database.DBQuere.DBQuery;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlerEditsInDB {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ChoiceBDEdit;

    @FXML
    private Button buttonN1;

    @FXML
    private Button buttonN2;

    @FXML
    private Button buttonN3;

    @FXML
    private Button buttonN4;

    @FXML
    private Button buttonNext;

    @FXML
    private ChoiceBox<String> choiceBox;
    DBQuery dbQuery = new DBQuery();
    @FXML
    void initialize() {

        buttonN1.setOnAction(event -> handleButtonN1Action());
        buttonN2.setOnAction(event -> handleButtonN2Action());
        buttonN3.setOnAction(event -> handleButtonN3Action());
        buttonN4.setOnAction(event -> handleButtonN4Action());

        choiceBox.getItems().addAll("Работа с абонементами", "Просмотр Членов Клуба", "Просмотр платежей");


        choiceBox.setOnAction(event -> {
            String selectedChoice = choiceBox.getValue();
            switch (selectedChoice) {
                case "Работа с абонементами":
                    configureButtons("Добавление", "Изменение", "Удаление", "Просмотр");
                    break;
                case "Просмотр Членов Клуба":
                    configureButtons("Просмотр", "", "", "");
                    break;
                case "Просмотр платежей":
                    configureButtons("Просмотр", "", "", "");
                    break;
            }
        });
        buttonNext.setText("Выйти");
        buttonNext.setOnAction(event -> {
            Stage stage = (Stage) buttonNext.getScene().getWindow();
            stage.close();
        });
    }

    private void configureButtons(String text1, String text2, String text3, String text4) {
        buttonN1.setText(text1);
        buttonN2.setText(text2);
        buttonN3.setText(text3);
        buttonN4.setText(text4);

        buttonN1.setVisible(!text1.isEmpty());
        buttonN2.setVisible(!text2.isEmpty());
        buttonN3.setVisible(!text3.isEmpty());
        buttonN4.setVisible(!text4.isEmpty());
    }

    private void handleButtonN1Action() {
        String selectedChoice = choiceBox.getValue();
        switch (selectedChoice) {
            case "Работа с абонементами":
                dbQuery.addNewAboniment(DialogWithDannie("Введите название абонемента"), DialogWithDannie("Введите срок"), DialogWithDannie("Введите цену"));
                break;
            case "Просмотр Членов Клуба":
                dbQuery.ViewAllRecodsMembersHip();
                break;
            case "Просмотр платежей":
                dbQuery.ViewAllRecodsOplata();
                break;
        }
    }


    private void handleButtonN2Action() {
        String selectedChoice = choiceBox.getValue();
        switch (selectedChoice) {
            case "Работа с абонементами":
                dbQuery.updateAboniments(Integer.parseInt(DialogWithDannie("Введите ID Абонемента")),DialogWithDannie("Введите Название Абонемента"), DialogWithDannie("Введите Срок Абонемента"), DialogWithDannie("Введите Цену Абонемента") );
                break;
            case "Просмотр Членов Клуба":
                // Добавить членов клуба в базу данных
                break;
            case "Просмотр платежей":
                // Добавить просмотр платежей
                break;
        }
    }
    private void handleButtonN3Action() {
        String selectedChoice = choiceBox.getValue();
        switch (selectedChoice) {
            case "Работа с абонементами":
               dbQuery.deleteRecordAboniment(Integer.parseInt(DialogWithDannie("Введите ID Абонемента")));
                break;
            case "Просмотр Членов Клуба":
                // Добавить членов клуба в базу данных
                break;
            case "Просмотр платежей":
                // Добавить просмотр платежей
                break;
        }
    }
    private void handleButtonN4Action() {
        String selectedChoice = choiceBox.getValue();
        switch (selectedChoice) {
            case "Работа с абонементами":
                dbQuery.ViewAllRecodsAboniment();
                break;
            case "Просмотр Членов Клуба":
                // Добавить членов клуба в базу данных
                break;
            case "Просмотр платежей":
                // Добавить просмотр платежей
                break;
        }
    }

    private String DialogWithDannie(String someInfo) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Что-то происходит");
        dialog.setHeaderText(null);
        dialog.setContentText(someInfo);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}

