/**
 * Test class for the {@link HomeViewInteractor}.
 * This class contains unit tests to verify the functionality of the HomeViewInteractor,
 * specifically to ensure that the appropriate methods in the output boundary
 * are called when switching views.
 */
package use_case.home_view_buttons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeViewInteractorTest {

    /**
     * This test verifies that the {@link HomeViewInteractor} correctly calls the
     * appropriate methods on the {@link HomeViewOutputBoundary} when switching views.
     *
     * Test Steps:
     * 1. A mock implementation of {@link HomeViewOutputBoundary} is created with boolean flags
     *    to track whether {@code switchToLoginView()} and {@code switchToSignupView()} are called.
     * 2. A {@link HomeViewInteractor} is instantiated with the mock presenter.
     * 3. The {@code switchToLoginView()} and {@code switchToSignupView()} methods of the
     *    interactor are called.
     * 4. Assertions are used to verify that both methods were called successfully.
     */
    @Test
    void successTest() {
        // Mock implementation of the output boundary
        HomeViewOutputBoundary successPresenter = new HomeViewOutputBoundary() {
            private boolean switchToLogInViewCalled;
            private boolean switchToSignupViewCalled;

            @Override
            public void switchToLoginView() {
                switchToLogInViewCalled = true;
            }

            @Override
            public boolean isSwitchToLoggedInViewCalled() {
                return switchToLogInViewCalled;
            }

            @Override
            public void switchToSignupView() {
                switchToSignupViewCalled = true;
            }

            @Override
            public boolean isSwitchToSignupViewCalled() {
                return switchToSignupViewCalled;
            }
        };

        // Instantiate interactor with the mock presenter
        HomeViewInputBoundary interactor = new HomeViewInteractor(successPresenter);

        // Call methods to switch views
        interactor.switchToLoginView();
        interactor.switchToSignupView();

        // Verify that the output boundary methods were called
        assertTrue(successPresenter.isSwitchToLoggedInViewCalled(),
                "The switchToLoginView method should have been called.");
        assertTrue(successPresenter.isSwitchToSignupViewCalled(),
                "The switchToSignupView method should have been called.");
    }
}
