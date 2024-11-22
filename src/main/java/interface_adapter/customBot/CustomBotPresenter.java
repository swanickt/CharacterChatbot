package interface_adapter.customBot;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.custom_bot.CustomViewOutputBoundary;

/**
 * The Presenter for the Custom Bot Use Case.
 */
public class CustomBotPresenter implements CustomViewOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final CustomBotViewModel customBotViewModel;

    public CustomBotPresenter(LoggedInViewModel loggedInViewModel,
                              ViewManagerModel viewManagerModel,
                              CustomBotViewModel customBotViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.customBotViewModel = customBotViewModel;
    }

    // @Override
    //    public void switchToChatBotApp() {
    //
    //    }

    @Override
    public void switchToLoggedinView() {
        final CustomBotState customBotState = customBotViewModel.getState();
        customBotState.setName("");
        customBotState.setOccupation("");
        customBotViewModel.setState(customBotState);
        customBotViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

