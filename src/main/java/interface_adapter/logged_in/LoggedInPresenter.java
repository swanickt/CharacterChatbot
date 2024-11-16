package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.customBot.CustomBotViewModel;
import interface_adapter.login.LoginState;
import use_case.logged_in.LoggedInOutputBoundary;

public class LoggedInPresenter implements LoggedInOutputBoundary {

    private final ChangePasswordViewModel changePasswordViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final CustomBotViewModel customBotViewModel;

    public LoggedInPresenter(ChangePasswordViewModel changePasswordViewModel,
                             ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             CustomBotViewModel customBotViewModel
                             ) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.customBotViewModel = customBotViewModel;
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

    @Override
    public void switchToCustomBotView() {
        viewManagerModel.setState(customBotViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}