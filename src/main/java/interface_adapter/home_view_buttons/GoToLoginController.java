package interface_adapter.home_view_buttons;

import use_case.home_view_buttons.HomeViewInputBoundary;

/**
 * The controller for the Home Screen use cases.
 */
public class GoToLoginController implements HomeviewController {

    private final HomeViewInputBoundary homeViewInteractor;

    public GoToLoginController(HomeViewInputBoundary homeViewInteractor) {
        this.homeViewInteractor = homeViewInteractor;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToView() {
        homeViewInteractor.switchToLoginView();
    }

}

