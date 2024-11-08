package use_case.logged_in;

public class LoggedInOutputData {

    private final String username;

    public LoggedInOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
