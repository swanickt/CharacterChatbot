package use_case.change_password;

import entity.user.User;
import entity.user.UserFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {

        if (!changePasswordInputData.getPassword().equals(changePasswordInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            final User user = userFactory.create(changePasswordInputData.getUsername(),
                    changePasswordInputData.getPassword());
            userDataAccessObject.changePassword(user);

            final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                    false);
            userPresenter.prepareSuccessView(changePasswordOutputData);
        }
    }

    @Override
    public void switchToLoggedInView() {
        userPresenter.switchToLoggedInView();
    }
}
