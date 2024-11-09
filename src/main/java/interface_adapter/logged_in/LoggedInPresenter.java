package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import use_case.logged_in.LoggedInOutputBoundary;

public class LoggedInPresenter implements LoggedInOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ChangePasswordViewModel changePasswordViewModel,
                             ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void switchToChangePasswordView() {
        final LoggedInState loggedInState = loggedInViewModel.getState();
        final ChangePasswordState changePasswordState = changePasswordViewModel.getState();
        changePasswordState.setPassword("");
        changePasswordState.setRepeatPassword("");
        changePasswordState.setUsername(loggedInState.getUsername());
        changePasswordViewModel.setState(changePasswordState);
        changePasswordViewModel.firePropertyChanged();

        viewManagerModel.setState(changePasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}