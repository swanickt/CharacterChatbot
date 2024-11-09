package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;

public class BackToLoggedInController {

    private final ChangePasswordInputBoundary changePasswordInteractor;

    public BackToLoggedInController(ChangePasswordInputBoundary changePasswordInputBoundary) {
        this.changePasswordInteractor = changePasswordInputBoundary;
    }

    /**
     * Executes the "switch to LoggedInView" Use Case.
     */
    public void switchToLoggedInView() {
        changePasswordInteractor.switchToLoggedInView();
    }
}
