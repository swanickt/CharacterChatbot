package use_case.chat.pikachu;

/**
 * The Input Data for the chat with Pikachu Use Case.
 */
public class PikachuInputData {
    private final String username;

    public PikachuInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}