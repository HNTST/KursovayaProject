package methods;

import javafx.scene.control.Alert;

public class miniMethods {

    public String[] FIOSplit(String fullName) {
        try {
            String[] parts = fullName.split("\\s+");
            String[] result = new String[3];

            if (parts.length >= 3) { // деление строки с Именем Фамилией Отчеством на разные переменные
                result[0] = parts[0]; // Имя
                result[1] = parts[1]; // Фамилия
                result[2] = parts[2]; // Отчество
                return result;
            } else {
                showAlert("Ошибка", "Не корректный ввод Фио");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String CheckPasswordInReg(String password, String passwordClone) {
        try {
            String result = "";
            if (password.equalsIgnoreCase(passwordClone)) {
                result = password;
            } else {
                showAlert("Ошибка", "Пароли не совпадают");
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String GendorInBd(String gender) {
        try {
            switch (gender) {
                case "м":
                    return "Мужчина";
                case "ж":
                    return "Женщина";
                default:
                    showAlert("Ошибка", "Пол введен некорректно");
                    return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String isPhoneNumberValid(String phoneNumber) {
        // Паттерн для проверки наличия только цифр в строке
        String pattern = "\\d+";

        // Проверяем, соответствует ли строка паттерну
        try {
            if( phoneNumber.matches(pattern)){
                if (phoneNumber.startsWith("+7") || phoneNumber.startsWith("+8")){
                    return  phoneNumber;
                }else {
                    String editedPhoneNumber = "+7" + phoneNumber;
                    return editedPhoneNumber;
                }

            }else{
                showAlert("Ошибка", "Номер телефона введен некорректно");
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




    private void showAlert(String title, String massage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(massage);
        alert.showAndWait();
    }

}
