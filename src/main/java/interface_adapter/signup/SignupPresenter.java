package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.home_view_buttons.HomeViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final HomeViewModel homeViewModel;
    private boolean switchToHomeViewCalled;
    private boolean switchToLoginViewCalled;


    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.homeViewModel = homeViewModel;
        this.switchToHomeViewCalled = false;
        this.switchToLoginViewCalled = false;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.

        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(null);
        signupState.setUsername("");
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        switchToLoginViewCalled = true;
    }

    @Override
    public boolean isSwitchedToLoginViewCalled() {
        return switchToLoginViewCalled;
    }

    @Override
    public void switchToHomeView() {
        switchToHomeViewCalled = true;
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(null);
        signupState.setUsername("");
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();
        // The above lines make it so that when we click cancel,
        // the textfields get cleared.

        viewManagerModel.setState(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public boolean isSwitchToHomeViewCalled() {
        return switchToHomeViewCalled;
    }
}
