package use_case.custom_bot;

import use_case.logged_in.LoggedInOutputBoundary;

public class CustomViewInteractor implements CustomViewInputBoundary {
    private final CustomViewOutputBoundary customPresenter;

    public CustomViewInteractor(CustomViewOutputBoundary customPresenter) {
        this.customPresenter = customPresenter;
    }

    //    public void switchToChatBotApp() {
    //        customPresenter.switchToChatBotApp();
    //    }

    public void switchToLoggedinView() {
        customPresenter.switchToLoggedinView();
    }
}
