package methods;

public class myPerson {
    private static String FullName;
    private static String Aboniment;
    private static String login;
    private static String password;

    public static String getFullName() {
        return FullName;
    }

    public static void setFullName(String fullName) {
        myPerson.FullName = fullName;
    }


    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        myPerson.login = login;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        myPerson.password = password;

    }

    public static void setAboniment(String aboniment) {
        myPerson.Aboniment = aboniment;
    }

    public static String getAboniment() {
        return Aboniment;
    }
}

