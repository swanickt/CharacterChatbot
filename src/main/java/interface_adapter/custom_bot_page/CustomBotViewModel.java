package interface_adapter.custom_bot_page;

import interface_adapter.ViewModel;

/**
 * The View Model for the Custom Bot View.
 */
public class CustomBotViewModel extends ViewModel<CustomBotState> {
    public CustomBotViewModel() {
        super("customBot");
        setState(new CustomBotState());
    }
}
