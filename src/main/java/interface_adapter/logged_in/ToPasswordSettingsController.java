package interface_adapter.logged_in;

import use_case.logged_in.LoggedInInputBoundary;

public class ToPasswordSettingsController {
    private final LoggedInInputBoundary toPasswordSettingsInteractor;

    public ToPasswordSettingsController(LoggedInInputBoundary toPasswordSettingsInteractor) {
        this.toPasswordSettingsInteractor = toPasswordSettingsInteractor;
    }

    /**
     * Executes the go to password settings page use case.
     */
    public void switchToChangePasswordView() {
        toPasswordSettingsInteractor.switchToChangePasswordView();
    }
}
