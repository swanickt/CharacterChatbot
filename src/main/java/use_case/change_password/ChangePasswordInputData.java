package use_case.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String repeatPassword;
    private final String username;

    public ChangePasswordInputData(String password, String repeatPassword, String username) {
        this.password = password;
        this.username = username;
        this.repeatPassword = repeatPassword;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    String getRepeatPassword() { return repeatPassword; }

}
