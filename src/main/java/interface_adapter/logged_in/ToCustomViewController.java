package interface_adapter.logged_in;

import use_case.logged_in.LoggedInInputBoundary;

public class ToCustomViewController {
    private final LoggedInInputBoundary toCustomInteractor;

    public ToCustomViewController(LoggedInInputBoundary toCustomInteractor) {
        this.toCustomInteractor = toCustomInteractor;
    }

    public void switchToCustomBotView() {
        toCustomInteractor.switchToCustomBotView();
    }
}
