package interface_adapter.custom_bot_page;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.exit_custom_bot_view.CustomViewOutputBoundary;

/**
 * The Presenter for the Custom Bot Use Case.
 */
public class CustomBotPagePresenter implements CustomViewOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final CustomBotViewModel customBotViewModel;
    private boolean switchToLoggedInViewCalled;

    public CustomBotPagePresenter(LoggedInViewModel loggedInViewModel,
                                  ViewManagerModel viewManagerModel,
                                  CustomBotViewModel customBotViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.customBotViewModel = customBotViewModel;
        this.switchToLoggedInViewCalled = false;
    }

    // @Override
    //    public void switchToChatBotApp() {
    //
    //    }

    @Override
    public void switchToLoggedinView() {
        switchToLoggedInViewCalled = true;
        final CustomBotState customBotState = customBotViewModel.getState();
        customBotState.setName("");
        customBotState.setOccupation("");
        customBotViewModel.setState(customBotState);
        customBotViewModel.firePropertyChanged();

        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public boolean isSwitchToLoggedInViewCalled() {
        return switchToLoggedInViewCalled;
    }
}

