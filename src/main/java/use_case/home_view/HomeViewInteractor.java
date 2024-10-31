package use_case.home_view;

/**
 * The HomeView Interactor.
 */
public class HomeViewInteractor implements HomeViewInputBoundary {

    private final HomeViewOutputBoundary homeViewPresenter;

    public HomeViewInteractor(HomeViewOutputBoundary homeViewPresenter) {
        this.homeViewPresenter = homeViewPresenter;
    }

    @Override
    public void switchToLoginView() {
        homeViewPresenter.switchToLoginView();
    }

    @Override
    public void switchToSignupView() {
        homeViewPresenter.switchToSignupView();
    }

}

