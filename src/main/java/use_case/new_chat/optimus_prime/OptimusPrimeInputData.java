package use_case.new_chat.optimus_prime;

/**
 * The Input Data for the Optimus Prime chat Use Case.
 */
public class OptimusPrimeInputData {
    private final String username;

    public OptimusPrimeInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
