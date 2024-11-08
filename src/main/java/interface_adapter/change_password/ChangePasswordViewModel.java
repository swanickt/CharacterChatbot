package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The View Model for the change password View.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public static final String TITLE_LABEL = "Password Settings";
    public static final String PASSWORD_LABEL = "New Password";
    public static final String REPEAT_PASSWORD_LABEL = "Re-enter new password";

    public static final String CHANGE_PASSWORD_BUTTON_LABEL = "Change Password";
    public static final String CANCEL_BUTTON_LABEL = "Back";

    public ChangePasswordViewModel() {
        super("change password");
        setState(new ChangePasswordState());
    }
}
