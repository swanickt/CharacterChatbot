package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.ChatBotSwingApp;
import entity.bot.*;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in.ToPasswordSettingsController;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    // private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ToPasswordSettingsController toPasswordSettingsController;

    private final JButton chatButton;
    private final JButton chatHistoryButton;
    private final JLabel username;
    private final JButton logOut;
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        // Title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        final JLabel title = new JLabel("Character Chatbot for");
        // Create radiobutton
        final JRadioButton normalBotButton = new JRadioButton("Normal Bot");
        final JRadioButton pickachuButton = new JRadioButton("Pickachu");
        final JRadioButton masterYodaButton = new JRadioButton("Master Yoda");
        final JRadioButton optimusPrimeButton = new JRadioButton("Optimus Prime");
        username = new JLabel();

        titlePanel.add(title);
        titlePanel.add(username);
        // Title label
        // final JLabel title = new JLabel("Character Chatbot for");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username information
        // final JLabel usernameInfo = new JLabel("Currently logged in: ");
        // username = new JLabel();

        // Password input field and change password button
        final JPanel passwordInfo = new JPanel();
        //        passwordInfo.setLayout(new BoxLayout(passwordInfo, BoxLayout.X_AXIS));
        //        passwordInfo.add(new JLabel("Password: "));
        passwordInfo.add(passwordInputField);

        // Initialize buttons
        chatButton = new JButton("Chat");
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Starting Chat...");
            String setting = "";
            if (normalBotButton.isSelected()) {
                setting = new NormalAIFactory().create().getPrompt();
            }
            else if (pickachuButton.isSelected()) {
                setting = new PikachuFactory().create().getPrompt();
            }
            else if (masterYodaButton.isSelected()) {
                setting = new MasterYodaFactory().create().getPrompt();
            }
            else if (optimusPrimeButton.isSelected()) {
                setting = new OptimusPrimeFactory().create().getPrompt();
            }
            final ChatBotSwingApp chatApp = new ChatBotSwingApp(setting);
            chatApp.setVisible(true);
        });

        chatHistoryButton = new JButton("Chat History");
        chatHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatHistoryButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Opening Chat History...");
        });

        changePassword = new JButton("Change Password");
        changePassword.addActionListener(evt -> {
            toPasswordSettingsController.switchToChangePasswordView();
        });

        logOut = new JButton("Log Out");
        logOut.addActionListener(evt -> {
            final LoggedInState currentState = loggedInViewModel.getState();
            logoutController.execute(currentState.getUsername());
        });

        final JLabel select = new JLabel("  (Select a character and click chat)");

        normalBotButton.setSelected(true);

        final JPanel characterPanel = new JPanel();
        // ensure only one button can be selected
        final ButtonGroup group = new ButtonGroup();
        group.add(normalBotButton);
        group.add(pickachuButton);
        group.add(masterYodaButton);
        group.add(optimusPrimeButton);

        characterPanel.add(normalBotButton);
        characterPanel.add(pickachuButton);
        characterPanel.add(masterYodaButton);
        characterPanel.add(optimusPrimeButton);

        characterPanel.add(select);
        characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.Y_AXIS));

        // Layout setup
        this.setLayout(new BorderLayout());

        // chatButton
        // final JLabel chatLabel = new JLabel("Normal Bot:");
        // final JPanel chatButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        // chatButtonPanel.add(chatLabel);
        // chatButtonPanel.add(characterPanel);
        // chatButtonPanel.add(chatButton);

        // chathistoryButton
        //        final JPanel chathistoryButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //        chathistoryButtonPanel.add(chatHistoryButton);

        // logoutChangePasswordButton
        //        final JPanel lcButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //        lcButtonPanel.add(changePassword);
        //        lcButtonPanel.add(logOut);

        // right vertical panel
        final JPanel rightVerticalPanel = new JPanel();
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightVerticalPanel.add(chatButton);
        rightVerticalPanel.add(chatHistoryButton);
        rightVerticalPanel.add(changePassword);
        rightVerticalPanel.add(logOut);
        rightVerticalPanel.setLayout(new BoxLayout(rightVerticalPanel, BoxLayout.Y_AXIS));
        // right part
        // final JPanel threeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        // threeButtonPanel.add(rightVerticalPanel);

        // Button panel
        // final JPanel buttons = new JPanel();
        // buttons.add(chatButtonPanel);
        // buttons.add(threeButtonPanel);
        // buttons.add(chathistoryButtonPanel);
        // buttons.add(lcButtonPanel);
        // buttons.add(characterPanel);
        // buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        // Add components to the main panel
        // this.add(title);
        // this.add(username);
        // this.add(titlePanel);
        // Ethis.add(passwordInfo);
        // this.add(passwordErrorField);
        this.add(rightVerticalPanel, BorderLayout.EAST);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(characterPanel, BorderLayout.WEST);

        // Document listener for password field
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "Password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setToPasswordSettingsController(ToPasswordSettingsController toPasswordSettingsController) {
        this.toPasswordSettingsController = toPasswordSettingsController;
    }
}