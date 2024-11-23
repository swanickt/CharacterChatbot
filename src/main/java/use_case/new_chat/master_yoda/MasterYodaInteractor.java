package use_case.new_chat.master_yoda;

import entity.bot.BotFactory;

public class MasterYodaInteractor implements MasterYodaInputBoundary {
    private final MasterYodaOutputBoundary masterYodaPresenter;
    private final BotFactory masterYodaFactory;

    public MasterYodaInteractor(MasterYodaOutputBoundary masterYodaPresenter,
                               BotFactory masterYodaFactory) {
        this.masterYodaPresenter = masterYodaPresenter;
        this.masterYodaFactory = masterYodaFactory;
    }

    @Override
    public void execute(MasterYodaInputData masterYodaInputData) {
        // retrieves the current user's username from the input data
        final String username = masterYodaInputData.getUsername();
        // creates a master yoda bot object and retrieves the relevant backend prompt to start the chat.
        final String prompt = masterYodaFactory.create().getPrompt();
        // create a pikachu output data object with the above information
        final MasterYodaOutputData masterYodaOutputData = new MasterYodaOutputData(username, prompt);

        masterYodaPresenter.beginChat(masterYodaOutputData);
    }
}
