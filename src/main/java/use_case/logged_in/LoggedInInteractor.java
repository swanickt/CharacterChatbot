package use_case.logged_in;

public class LoggedInInteractor implements LoggedInInputBoundary {
    private final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary loggedInPresenter) {
        this.loggedInPresenter = loggedInPresenter;
    }

    public void switchToChangePasswordView() { loggedInPresenter.switchToChangePasswordView(); }
}
