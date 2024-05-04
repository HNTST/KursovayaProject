package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import methods.myPerson;
import javafx.stage.Stage;
import sample.ControlerPersonalAcc;
import com.database.DBQuere.Aboniment;
import com.database.DBQuere.DBQuery;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlerOplataAbonimenta {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOplata;

    @FXML
    private ChoiceBox<String> choiceBoxAbonimnt;

    @FXML
    private Label textDateChange;

    @FXML
    private Label textPriceChange;

    String nameAbon = "";

    @FXML
    void initialize() {
        DBQuery dbQuery = new DBQuery();
        Aboniment[] aboniments = dbQuery.getAboniments();
        ControlerPersonalAcc controlerPersonalAcc = new ControlerPersonalAcc();
        myPerson myPerson = new myPerson();

        for (Aboniment aboniment : aboniments) {
            choiceBoxAbonimnt.getItems().add(aboniment.getName());
        }
        choiceBoxAbonimnt.setOnAction(event -> {
           String  selectedAboniment = choiceBoxAbonimnt.getValue();
           nameAbon = selectedAboniment;
            Aboniment selectedAbonimentInfo = getAbonimentInfo(selectedAboniment);
            myPerson.setAboniment(selectedAboniment);


            // Обновляем текст на метках
            textDateChange.setText(selectedAbonimentInfo.getDuration());
            textPriceChange.setText(selectedAbonimentInfo.getPrice());

        });

        buttonOplata.setOnAction(event -> {
            try {
                Aboniment selectedAbonInfo = getAbonimentInfo(nameAbon);
                dbQuery.addPayment(selectedAbonInfo.getPrice(), selectedAbonInfo.getName(),dbQuery.idChelnaCluba(dbQuery.getFio(myPerson.getLogin())) );
                dbQuery.addAbonimentToUser(dbQuery.getFio(myPerson.getLogin()), myPerson.getAboniment());
                Parent root = FXMLLoader.load(getClass().getResource("personalAccount.fxml"));
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/sticker.png"));
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) buttonOplata.getScene().getWindow();// закрытие окна
            stage.close();

        });
    }




    private Aboniment getAbonimentInfo(String abonimentName) {
        DBQuery dbQuery = new DBQuery();
        Aboniment[] aboniments = dbQuery.getAboniments();
        for (Aboniment aboniment : aboniments) {
            if (aboniment.getName().equals(abonimentName)) {
                return aboniment;
            }
        }
        return null; // Обработка ситуации, если абонимент не найден
    }


}
