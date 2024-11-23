package interface_adapter.custom_bot;

import use_case.exit_custom_bot_view.CustomViewInputBoundary;

public class GoBackToLoggedInViewController {
    private final CustomViewInputBoundary customViewInteractor;

    public GoBackToLoggedInViewController(CustomViewInputBoundary customViewInteractor) {
        this.customViewInteractor = customViewInteractor;
    }

    /**
     * Executes the "switch to LoggedIn View" Use Case.
     */
    public void switchToLoggedInView() {
        customViewInteractor.switchToLoggedinView();
    }
}
