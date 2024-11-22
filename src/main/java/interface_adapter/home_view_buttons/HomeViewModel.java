package interface_adapter.home_view_buttons;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the HomeView (Home Screen).
 */
public class HomeViewModel extends ViewModel<HomeViewState> {

    public static final String TITLE1_LABEL = "Welcome to Character Chatbot!";
    public static final String TITLE2_LABEL = "(Beta Version)";
    public static final String LOGIN_BUTTON_LABEL = "Go to Login";
    public static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    public static final String CLOSE_APP_BUTTON_LABEL = "Close App";

    public HomeViewModel() {
        super("home view");
        setState(new HomeViewState());
    }
}
