package use_case.home_view_buttons;

/**
 * The output boundary for the Home screen Use Cases.
 */
public interface HomeViewOutputBoundary {

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    boolean isSwitchToLoggedInViewCalled();

    /**
     * Switches to the Signup View.
     */
    void switchToSignupView();

    boolean isSwitchToSignupViewCalled();
}
