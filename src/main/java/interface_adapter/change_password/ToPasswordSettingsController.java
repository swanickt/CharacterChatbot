package interface_adapter.change_password;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInputData;

public class ToPasswordSettingsController {
    private final LoggedInInputBoundary toPasswordSettingsInteractor;

    public ToPasswordSettingsController(LoggedInInputBoundary toPasswordSettingsInteractor) {
        this.toPasswordSettingsInteractor = toPasswordSettingsInteractor;
    }

    public void switchToChangePasswordView() {
        toPasswordSettingsInteractor.switchToChangePasswordView();
    }
}
