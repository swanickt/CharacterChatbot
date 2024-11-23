package interface_adapter.new_chat.pikachu;

import use_case.new_chat.pikachu.PikachuInputBoundary;
import use_case.new_chat.pikachu.PikachuInputData;

/**
 * The controller for the chatting with Pikachu Use case.
 */
public class PikachuController {
    private final PikachuInputBoundary pikachuInteractor;

    public PikachuController(PikachuInputBoundary pikachuInteractor) {
        this.pikachuInteractor = pikachuInteractor;
    }

    /**
     * Executes the chat with Pikachu Use Case.
     * @param username the username of the user chatting.
     */
    public void execute(String username) {
        final PikachuInputData pikachuInputData = new PikachuInputData(username);

        pikachuInteractor.execute(pikachuInputData);
    }
}
