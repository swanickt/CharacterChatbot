package interface_adapter.login;

import use_case.login.LoginInputBoundary;

public class CancelController {
    private final LoginInputBoundary loginUseCaseInteractor;

    public CancelController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        loginUseCaseInteractor.switchToHomeView();
    }
}
