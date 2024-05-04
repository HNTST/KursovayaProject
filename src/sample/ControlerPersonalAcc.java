package sample;

import com.database.DBQuere.DBQuery;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import  methods.myPerson;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControlerPersonalAcc {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonClose;

    @FXML
    private Label FIOUpChange;

    @FXML
    private Text abonumntText;

    @FXML
    private Button buttonBuyAboniment;

    @FXML
    private Label loginChage;

    @FXML
    private Label passwordChange;

    @FXML
    private Text textAbonimentChange;

    @FXML
    private Label textAbonumentChange;

    @FXML
    private Text textNumberPhone;

    @FXML
    private Text textNumberPhone1;

    @FXML
    private Label textNumberPhoneChange;

    @FXML
    private Text textNumberPhoneChange11;




    @FXML
    void initialize() {
        DBQuery dbQuery = new DBQuery();
        myPerson myPerson = new myPerson();


        FIOUpChange.setText(dbQuery.getFio(myPerson.getLogin()));
        loginChage.setText(myPerson.getLogin());
        passwordChange.setText(myPerson.getPassword());
        textNumberPhoneChange.setText(dbQuery.getPhoneNumber(myPerson.getLogin()));

        if (dbQuery.checkAbonimentUserInDB(dbQuery.getFio(myPerson.getLogin())) != null){
            textAbonumentChange.setText(dbQuery.checkAbonimentUserInDB(dbQuery.getFio(myPerson.getLogin())));
        }else {
            textAbonumentChange.setText("Абонемент не куплен");
            buttonBuyAboniment.setDisable(false);
            buttonBuyAboniment.setOnAction(event -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("oplataAboniumenta.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                    textAbonumentChange.setText(myPerson.getAboniment());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage) buttonBuyAboniment.getScene().getWindow(); // закрытие окна Старт
                stage.close();
            });
        }


        ButtonClose.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/sample/startWindow.fxml"));
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/sticker.png"));
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) ButtonClose.getScene().getWindow(); // закрытие окна
            stage.close();
        });




    }




}
