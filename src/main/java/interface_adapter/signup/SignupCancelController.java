package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;

public class SignupCancelController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupCancelController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToHomeView() {
        userSignupUseCaseInteractor.switchToHomeView();
    }
}
