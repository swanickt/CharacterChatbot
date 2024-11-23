package use_case.signup;

/**
 * The output boundary for the Signup Use Case.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SignupOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    boolean isSwitchedToLoginViewCalled();
    /**
     * Switches to the Home View.
     */
    void switchToHomeView();

    boolean isSwitchToHomeViewCalled();
}
