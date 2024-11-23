package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the LoginInteractor use case.
 * Contains tests to verify success and failure scenarios for user login.
 */
class LoginInteractorTest {

    /**
     * Tests the successful login flow.
     * Verifies that a user with valid credentials can log in,
     * and ensures that the username and password fields are cleared and no error is present after login.
     */
    @Test
    void successTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add a user to the repository before attempting login.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            private boolean switchToHomeViewCalled = false;

            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToHomeView() {
                switchToHomeViewCalled = true;
            }

            public boolean isSwitchToHomeViewCalled() {
                return switchToHomeViewCalled;
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);

        // Verify login state after successful login
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState loginState = loginViewModel.getState();
        assertEquals("", loginState.getUsername(), "Username should be cleared");
        assertEquals("", loginState.getPassword(), "Password should be cleared");
        assertNull(loginState.getLoginError(), "LoginError should be null");

        interactor.switchToHomeView();
        assertTrue(successPresenter.isSwitchToHomeViewCalled());
    }

    /**
     * Tests the successful login flow and verifies that the user is marked as logged in.
     */
    @Test
    void successUserLoggedInTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add a user to the repository before attempting login.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            private boolean switchToHomeViewCalled = false;

            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Paul", userRepository.getCurrentUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToHomeView() {
                switchToHomeViewCalled = true;
            }

            @Override
            public boolean isSwitchToHomeViewCalled() {
                return switchToHomeViewCalled;
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter);
        assertNull(userRepository.getCurrentUsername());

        interactor.execute(inputData);
        interactor.switchToHomeView();
        assertTrue(successPresenter.isSwitchToHomeViewCalled());
    }

    /**
     * Tests the failure scenario where the user enters an incorrect password.
     * Verifies that an appropriate error message is displayed.
     */
    @Test
    void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Paul", "wrong");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add a user to the repository with a different password.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for \"Paul\".", error);
            }

            @Override
            public void switchToHomeView() {
                // No action needed
            }

            @Override
            public boolean isSwitchToHomeViewCalled() {
                return false;
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    /**
     * Tests the failure scenario where the user does not exist in the repository.
     * Verifies that an appropriate error message is displayed.
     */
    @Test
    void failureUserDoesNotExistTest() {
        LoginInputData inputData = new LoginInputData("Paul", "password");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Paul: Account does not exist.", error);
            }

            @Override
            public void switchToHomeView() {
                // No action needed
            }

            @Override
            public boolean isSwitchToHomeViewCalled() {
                return false;
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
