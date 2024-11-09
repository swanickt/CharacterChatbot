package interface_adapter.login;

import use_case.login.LoginInputBoundary;

public class LoginCancelController {
    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginCancelController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        loginUseCaseInteractor.switchToHomeView();
    }
}
