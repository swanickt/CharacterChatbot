package use_case.exit_custom_bot_view;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomViewInteractorTest {
    /**
     * Tests the successful exit button.
     * Verifies that if the user can go back to LoggedInView,
     */
    @Test
    public void successTest() {
        CustomViewOutputBoundary successPresenter = new CustomViewOutputBoundary() {
            private boolean switchToLoggedInViewCalled;
            @Override
            public void switchToLoggedinView() {
                switchToLoggedInViewCalled = true;
            }

            @Override
            public boolean isSwitchToLoggedInViewCalled() {
                return switchToLoggedInViewCalled;
            }
        };
       CustomViewInputBoundary iteractor = new CustomViewInteractor(successPresenter);
       iteractor.switchToLoggedinView();
       assertTrue(successPresenter.isSwitchToLoggedInViewCalled());
    }
}
