package interface_adapter.logged_in;

import use_case.logged_in.LoggedInInputBoundary;

public class ToPasswordSettingsController {
    private final LoggedInInputBoundary toPasswordSettingsInteractor;

    public ToPasswordSettingsController(LoggedInInputBoundary toPasswordSettingsInteractor) {
        this.toPasswordSettingsInteractor = toPasswordSettingsInteractor;
    }

    public void switchToChangePasswordView() {
        toPasswordSettingsInteractor.switchToChangePasswordView();
    }
}
