package use_case.signup;

import data_access.DBchatuser;
import entity.user.User;
import entity.user.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;
    private final DBchatuser dbchatuser;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        dbchatuser = new DBchatuser();
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (dbchatuser.checkDuplicate(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }

        else {
            dbchatuser.saveUserAccount(signupInputData.getUsername(),
                    signupInputData.getUsername(), signupInputData.getPassword());
            final String name = dbchatuser.getUserNameAndPassword(signupInputData.getUsername()).get("userName");

            final SignupOutputData signupOutputData = new SignupOutputData(name, false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }

    @Override
    public void switchToHomeView() {
        userPresenter.switchToHomeView();
    }
}
