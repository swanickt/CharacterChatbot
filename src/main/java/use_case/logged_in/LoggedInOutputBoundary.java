package use_case.logged_in;

public interface LoggedInOutputBoundary {

    /**
     * Switches to the change password View.
     */
    void switchToChangePasswordView();

    void switchToCustomBotView();
}
