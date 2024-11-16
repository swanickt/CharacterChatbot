package interface_adapter.customBot;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.custom_bot.CustomViewOutputBoundary;
import view.ChatBotSwingApp;

/**
 * The Presenter for the Custom Bot Use Case.
 */
public class CustomBotPresenter implements CustomViewOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public CustomBotPresenter(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    // @Override
    //    public void switchToChatBotApp() {
    //
    //    }

    @Override
    public void switchToLoggedinView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

