package com.database.DBQuere;

import com.database.DBConnection.DBConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQuery {

    public Boolean LoginPasswordDB(String enteredLogin, String enteredPassword) throws SQLException {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM логины");

            while (resultSet.next()) {
                String loginDB = resultSet.getNString("логин");
                String passwordDB = resultSet.getString("пароль");


                if (loginDB.equalsIgnoreCase(enteredLogin) && passwordDB.equalsIgnoreCase(enteredPassword)) {
                    resultSet.close();
                    statement.close();
                    connection.close();
                    return true;
                }
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    public void addMember(String firstName, String lastName, String middleName, Date date,
                          String numberPhone, String contactInfo, String gender, String login) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO членыклуба (Имя, Фамилия, Отчество, Дата_рождения, Пол, Номер_Телефона, Контактная_информация, Login) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, middleName);
            preparedStatement.setDate(4, date);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, numberPhone);
            preparedStatement.setString(7, contactInfo);
            preparedStatement.setString(8, login);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addAbonimentToUser(String fullName, String nameAboniment) {
        String[] part = fullName.split(" ");
        String firstName = part[0];
        String lastName = part[1];
        String middleName = part[2];

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement selectStatement = connection.prepareStatement("SELECT ID_ЧленаКлуба FROM членыклуба WHERE Имя = ? AND Фамилия = ? AND Отчество = ?");
            selectStatement.setString(1, firstName);
            selectStatement.setString(2, lastName);
            selectStatement.setString(3, middleName);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("ID_ЧленаКлуба");
                PreparedStatement insertStatement = connection.prepareStatement("UPDATE членыклуба SET Тип_Абонемента_Название_Абонемента = ? WHERE ID_ЧленаКлуба = ?");
                insertStatement.setString(1, nameAboniment);
                insertStatement.setInt(2, userId);
                insertStatement.executeUpdate();
            } else {
                showAlert("Ошибка", "Пользователь не найден");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int idChelnaCluba(String fullName) {
        String[] part = fullName.split(" ");
        String firstName = part[0];
        String lastName = part[1];
        String middleName = part[2];
        int userID = 0;

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement selectStatement = connection.prepareStatement("SELECT ID_ЧленаКлуба FROM членыклуба WHERE Имя = ? AND Фамилия = ? AND Отчество = ?");
            selectStatement.setString(1, firstName);
            selectStatement.setString(2, lastName);
            selectStatement.setString(3, middleName);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                userID = resultSet.getInt("ID_ЧленаКлуба");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }

    public void addLoginPassword(String login, String password,String fullName) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO логины (логин, пароль, ID_Члена_Клуба) VALUES (?,?,?)");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, idChelnaCluba(fullName));
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String checkAbonimentUserInDB(String fullName) {
        String[] part = fullName.split(" ");
        String firstName = part[0];
        String lastName = part[1];
        String middleName = part[2];

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement selectStatement = connection.prepareStatement("SELECT Тип_Абонемента_Название_Абонемента FROM членыклуба WHERE Имя = ? AND Фамилия = ? AND Отчество = ?");
            selectStatement.setString(1, firstName);
            selectStatement.setString(2, lastName);
            selectStatement.setString(3, middleName);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                String abonimentName = resultSet.getString("Тип_Абонемента_Название_Абонемента");
                return abonimentName;
            } else {
                showAlert("Ошибка", "Пользователь не найден");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String CheckLoginInBD(String login) {
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM логины");

            boolean loginExist = false;

            while (resultSet.next()) {
                String loginDB = resultSet.getString("логин");
                if (login.equalsIgnoreCase(loginDB)) {
                    loginExist = true;
                    break;
                }
            }
            if (loginExist) {
                showAlert("Ошибка", "Такой логин уже существует");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    public void deleteRecordAboniment(int id) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "DELETE FROM тип_абонемента WHERE idТип_Абонемента = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            showAlert("Готова", "Запись Удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ViewAllRecodsAboniment() {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT * FROM тип_абонемента";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            TextArea textArea = new TextArea();

            while (resultSet.next()) {
                int id = resultSet.getInt("idТип_Абонемента");
                String nameAbon = resultSet.getNString("Название_Абонемента");
                String srock = resultSet.getNString(3);
                String price = resultSet.getNString(4);
                textArea.appendText("ID: " + id + " ||| Название Абонемента: " + nameAbon + " ||| Срок: " + srock + " |||  Цена: " + price + "\n");
            }
            showAlertInformation("Результаты", textArea);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ViewAllRecodsOplata() {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT * FROM оплаты";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            TextArea textArea = new TextArea();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String price = resultSet.getString(2);
                int idCHlenaCLuba = resultSet.getInt(5);
                java.util.Date date = resultSet.getDate(3);
                String nameAbon = resultSet.getString(4);
                textArea.appendText("ID Платежа: " + id + " ||| Сумма Платежа: " + price + " ||| ID Члена Клуба: " + idCHlenaCLuba + " |||  Дата оплаты: " + date + " ||| Название Абонемента: " + nameAbon + "\n");
            }
            showAlertInformation("Результаты", textArea);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ViewAllRecodsMembersHip() {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "SELECT * FROM членыклуба";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            TextArea textArea = new TextArea();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String middleName = resultSet.getString(4);
                java.util.Date date = resultSet.getDate(5);
                String gender = resultSet.getString(6);
                String contactInfo = resultSet.getString(7);
                String nameAbon = resultSet.getString(8);
                String phoneNumber = resultSet.getString(9);
                String idLogin = resultSet.getString(12);
                textArea.appendText(
                                "ID Члена Клуба: " + id +
                                " ||| ID его логина: " + idLogin +
                                " ||| Имя Фамилия Отчество: " + firstName + " " + lastName + " " + middleName +
                                " ||| Дата рождения: " + date +
                                " ||| Пол: " + gender +
                                " ||| Номер телефона: " + phoneNumber +
                                " ||| Адрес: " + contactInfo +
                                " ||| Абонемент: " + nameAbon +
                                "\n");
            }
            showAlertInformation("Результаты", textArea);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPayment(String price, String nameAbon, int idChlenda) {
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO оплаты (сумма,id_ЧленаКлуба, Дата_Оплаты,Тип_Абонемента_Название_Абонемента) VALUES (?,?,?,?)");
            preparedStatement.setString(1, price);
            preparedStatement.setInt(2, idChlenda);
            preparedStatement.setDate(3, currentDate);
            preparedStatement.setString(4, nameAbon);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAboniments(int ID, String nameAbon, String srock, String price) {
        try {
            String sql = "UPDATE тип_абонемента SET Название_Абонемента = ?, срок = ?, цена = ? WHERE IDТип_Абонемента = ?";
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nameAbon);
            statement.setString(2, srock);
            statement.setString(3, price);
            statement.setInt(4, ID);
            statement.executeUpdate();
            showAlert("Готова", "Запись обновлена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFio(String login) {
        String[] nameSurnamePatronymic = new String[3]; // Имя, Фамилия, Отчество
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String fulName = "";

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT Имя, Фамилия, Отчество FROM членыклуба WHERE Login = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameSurnamePatronymic[0] = resultSet.getString("Имя");
                nameSurnamePatronymic[1] = resultSet.getString("Фамилия");
                nameSurnamePatronymic[2] = resultSet.getString("Отчество");
                fulName = nameSurnamePatronymic[0] + " " + nameSurnamePatronymic[1] + " " + nameSurnamePatronymic[2];
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем ресурсы в блоке finally
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return fulName;
    }

    public String getPhoneNumber(String login) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String phoneNumber = "";
        try {
            connection = DBConnection.getConnection();
            String query = "SELECT Номер_Телефона FROM членыклуба WHERE Login = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                phoneNumber = resultSet.getString("Номер_Телефона");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрываем ресурсы в блоке finally
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return phoneNumber;
    }


    public Aboniment[] getAboniments() {
        List<Aboniment> abonimentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM тип_абонемента";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Название_Абонемента");
                String price = resultSet.getString("цена");
                String duration = resultSet.getString("срок");

                Aboniment aboniment = new Aboniment(name, price, duration);
                abonimentList.add(aboniment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Преобразуем список в массив
        Aboniment[] abonimentArray = new Aboniment[abonimentList.size()];
        abonimentArray = abonimentList.toArray(abonimentArray);

        return abonimentArray;
    }

    public void addNewAboniment(String nameAboniment, String srock, String price) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO тип_абонемента (название_абонемента, срок, цена) VALUES (?,?,?)");
            preparedStatement.setString(1, nameAboniment);
            preparedStatement.setString(2, srock);
            preparedStatement.setString(3, price);

            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String massage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);

        ImageView icon = new ImageView(new Image("/sticker.png"));
        icon.setFitWidth(30); // Задаем ширину изображения
        icon.setFitHeight(30); // Задаем высоту изображения

        // Устанавливаем изображение в заголовок Alert
        alert.setGraphic(icon);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styleAlert.css").toExternalForm());

        alert.showAndWait();
    }

    private void showAlertInformation(String title, TextArea textArea) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(textArea);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styleAlert.css").toExternalForm());
        alert.showAndWait();
    }
}

