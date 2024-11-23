package use_case.exit_custom_bot_view;

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
