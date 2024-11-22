package interface_adapter.exit_chat;

import use_case.OptimusPrime.OptimusPrimeInputBoundary;
import use_case.OptimusPrime.OptimusPrimeInputData;
import use_case.exit_chat.ExitChatInputBoundary;

/**
 * The controller for the 'exit chat' Use case.
 */
public class ExitChatController {

    private final ExitChatInputBoundary exitChatInteractor;

    public ExitChatController(ExitChatInputBoundary exitChatInteractor) {
        this.exitChatInteractor = exitChatInteractor;
    }

    /**
     * Executes the exit/end chat Use Case.
     */
    public void execute() {

    }
}
