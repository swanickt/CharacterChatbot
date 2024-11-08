package interface_adapter.home_view;

import use_case.home_view.HomeViewInputBoundary;

public class GoToSignUpController implements HomeviewController {

    private final HomeViewInputBoundary homeViewInteractor;

    public GoToSignUpController(HomeViewInputBoundary homeViewInteractor) {
        this.homeViewInteractor = homeViewInteractor;
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToView() {
        homeViewInteractor.switchToSignupView();
    }
}
