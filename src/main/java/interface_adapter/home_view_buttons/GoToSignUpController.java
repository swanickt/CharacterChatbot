package interface_adapter.home_view_buttons;

import use_case.home_view_buttons.HomeViewInputBoundary;

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
