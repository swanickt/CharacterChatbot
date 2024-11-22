package interface_adapter.chat.master_yoda;

import use_case.chat.master_yoda.MasterYodaInputBoundary;
import use_case.chat.master_yoda.MasterYodaInputData;

/**
 * The controller for the chatting with Master Yoda bot Use case.
 */
public class MasterYodaController {
    private final MasterYodaInputBoundary masterYodaInteractor;

    public MasterYodaController(MasterYodaInputBoundary masterYodaInteractor) {
        this.masterYodaInteractor = masterYodaInteractor;
    }

    /**
     * Executes the chat with master yoda Use Case.
     * @param username the username of the user chatting.
     */
    public void execute(String username) {
        final MasterYodaInputData masterYodaInputData = new MasterYodaInputData(username);

        masterYodaInteractor.execute(masterYodaInputData);
    }
}