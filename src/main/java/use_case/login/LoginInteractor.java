package use_case.login;

import data_access.DBchatuser;
import entity.user.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;
    private final DBchatuser dbchatuser;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        dbchatuser = new DBchatuser();
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        final String hisuser = dbchatuser.getUserNameAndPassword(username).get("userName");
        final String hispassword = dbchatuser.getUserNameAndPassword(username).get("userPassword");
        if (!hisuser.equals(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            if (!password.equals(hispassword)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {
                final String name = dbchatuser.getUserNameAndPassword(username).get("userName");
                userDataAccessObject.setCurrentUsername(name);
                final LoginOutputData loginOutputData = new LoginOutputData(name, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToHomeView() {
        loginPresenter.switchToHomeView();
    }
}
