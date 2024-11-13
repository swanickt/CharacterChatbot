package interface_adapter.customBot;

import use_case.custom_bot.CustomViewInputBoundary;
import use_case.custom_bot.CustomViewInteractor;

public class GoBackToLogginViewController {
    private final CustomViewInputBoundary customViewInteractor;

    public GoBackToLogginViewController(CustomViewInputBoundary customViewInteractor) {
        this.customViewInteractor = customViewInteractor;
    }

    /**
     * Executes the "switch to HomeView" Use Case.
     */
    public void switchToLoggedInView() {
        customViewInteractor.switchToLoggedinView();
    }
}
