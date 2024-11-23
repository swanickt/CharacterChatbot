package interface_adapter.custom_bot;

import use_case.custom_bot.CustomViewInputBoundary;

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
