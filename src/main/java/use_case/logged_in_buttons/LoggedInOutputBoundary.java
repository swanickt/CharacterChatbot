package use_case.logged_in_buttons;

public interface LoggedInOutputBoundary {

    /**
     * Switches to the change password View.
     */
    void switchToChangePasswordView();

    boolean isSwitchToChangePasswordViewCalled();

    void switchToCustomBotView();

    boolean isSwitchToCustomBotViewCalled();
}
