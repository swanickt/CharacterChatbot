package interface_adapter.home_view;

import use_case.home_view.HomeViewInputBoundary;

/**
 * The controller for the Home Screen use cases.
 */
public class HomeViewController {

    private final HomeViewInputBoundary homeViewInteractor;

    public HomeViewController(HomeViewInputBoundary homeViewInteractor) {
        this.homeViewInteractor = homeViewInteractor;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        homeViewInteractor.switchToLoginView();
    }

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToSignupView() {
        homeViewInteractor.switchToSignupView();
    }
}

