package use_case.logged_in_buttons;

public class LoggedInInteractor implements LoggedInInputBoundary {
    private final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInPresenter) {
        this.loggedInPresenter = loggedInPresenter;
    }

    public void switchToChangePasswordView() { loggedInPresenter.switchToChangePasswordView(); }

    public void switchToCustomBotView() { loggedInPresenter.switchToCustomBotView(); }

}
