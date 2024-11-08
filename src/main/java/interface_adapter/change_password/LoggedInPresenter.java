package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
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