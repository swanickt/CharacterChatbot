package use_case.logged_in;

public class LoggedInInputData {

    private final String username;
    private final String password;

    public LoggedInInputData(String password, String username) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
