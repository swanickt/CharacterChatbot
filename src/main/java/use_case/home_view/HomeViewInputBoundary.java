package use_case.home_view;

/**
 * Input Boundary for actions which are related to home screen buttons.
 */
public interface HomeViewInputBoundary {

    /**
     * Executes the switch to signup view use case.
     */
    void switchToSignupView();

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
