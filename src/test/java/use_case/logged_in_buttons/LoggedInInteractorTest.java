package use_case.logged_in_buttons;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the LoggedInInteractor and related classes.
 * This class contains unit tests for methods in the LoggedInInteractor,
 * LoggedInOutputData, and LoggedInInputData classes to ensure their correctness.
 */
public class LoggedInInteractorTest {

    /**
     * Tests the interaction between LoggedInInteractor and LoggedInOutputBoundary.
     * This test ensures that the switchToChangePasswordView() and switchToCustomBotView() methods
     * correctly trigger the corresponding methods in the output boundary.
     */
    @Test
    void successTest() {
        LoggedInOutputBoundary successPresenter = new LoggedInOutputBoundary() {

            private boolean switchToChangePasswordViewCalled;
            private boolean switchToCustomBotViewCalled;

            @Override
            public void switchToChangePasswordView() {
                switchToChangePasswordViewCalled = true;
            }

            @Override
            public boolean isSwitchToChangePasswordViewCalled() {
                return switchToChangePasswordViewCalled;
            }

            @Override
            public void switchToCustomBotView() {
                switchToCustomBotViewCalled = true;
            }

            @Override
            public boolean isSwitchToCustomBotViewCalled() {
                return switchToCustomBotViewCalled;
            }
        };
        LoggedInInputBoundary interactor = new LoggedInInteractor(successPresenter);
        interactor.switchToChangePasswordView();
        interactor.switchToCustomBotView();
        assertTrue(successPresenter.isSwitchToChangePasswordViewCalled());
        assertTrue(successPresenter.isSwitchToCustomBotViewCalled());
    }

    /**
     * Test for the getUsername() method in LoggedInOutputData.
     * Verifies that the username returned by getUsername() matches the one provided to the constructor.
     */
    @Test
    void testGetUsername() {
        // Arrange
        String expectedUsername = "testUser";
        LoggedInOutputData outputData = new LoggedInOutputData(expectedUsername);

        // Act
        String actualUsername = outputData.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    /**
     * Test for the getUsername() method in LoggedInInputData.
     * Verifies that the username returned by getUsername() matches the one provided to the constructor.
     */
    @Test
    void testGetUsername1() {
        // Arrange
        String expectedUsername = "testUser";
        String dummyPassword = "testPass";
        LoggedInInputData inputData = new LoggedInInputData(dummyPassword, expectedUsername);

        // Act
        String actualUsername = inputData.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    /**
     * Test for the getPassword() method in LoggedInInputData.
     * Verifies that the password returned by getPassword() matches the one provided to the constructor.
     */
    @Test
    void testGetPassword() {
        // Arrange
        String expectedPassword = "testPass";
        String dummyUsername = "testUser";
        LoggedInInputData inputData = new LoggedInInputData(expectedPassword, dummyUsername);

        // Act
        String actualPassword = inputData.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }
}
