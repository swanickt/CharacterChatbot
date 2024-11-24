package use_case.change_password;

import data_access.InMemoryUserDataAccessObject;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import interface_adapter.change_password.ChangePasswordPresenter;
import use_case.login.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangePasswordInteractorTest {
    @Test
    void successTest(){
        ChangePasswordInputData inputData = new ChangePasswordInputData("abc","abc", "Jack");
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Jack", "password");
        userRepository.save(user);

        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            private boolean switchToLoggedInViewCalled = false;

            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                assertEquals("Jack", outputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Test should not reach failure path.");
            }

            @Override
            public void switchToLoggedInView() {
                switchToLoggedInViewCalled = true;
            }

            @Override
            public boolean isSwitchToLoggedInViewCalled() {
                return switchToLoggedInViewCalled;
            }
        };
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, successPresenter, factory);

        // Execute the use case
        interactor.execute(inputData);

        // Verify the password is updated in the repository
        assertEquals("abc", userRepository.get("Jack").getPassword());

        interactor.switchToLoggedInView();
        assertTrue(successPresenter.isSwitchToLoggedInViewCalled());
    }

    @Test
    void failurePasswordMismatchTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("abc","wrong", "Jack");
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add a user to the repository with a different password.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Jack", "password");
        userRepository.save(user);

        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {

            @Override
            public void prepareSuccessView(ChangePasswordOutputData outputData) {
                Assertions.fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                Assertions.assertEquals("Passwords don't match.", error);
            }

            public void switchToLoggedInView() {
                // not test here
            }

            @Override
            public boolean isSwitchToLoggedInViewCalled() {
                return false;
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, failurePresenter, factory);
        interactor.execute(inputData);
    }
}
