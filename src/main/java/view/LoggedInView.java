package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.chat.CommonUserChat;
import interface_adapter.exit_chat.ExitChatController;
import interface_adapter.new_chat.ChatViewModel;
import interface_adapter.new_chat.master_yoda.MasterYodaController;
import interface_adapter.new_chat.normal_bot.NormalBotController;
import interface_adapter.new_chat.optimus_prime.OptimusPrimeController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.past_chat.PastChatViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.new_chat.pikachu.PikachuController;
import interface_adapter.past_chat.PastChatController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in.ToPasswordSettingsController;
import interface_adapter.logout.LogoutController;
import interface_adapter.logged_in.ToCustomViewController;
import use_case.past_chat.PastChatInteractor;

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
    private ToCustomViewController toCustomViewController;
    private OptimusPrimeController optimusPrimeController;
    private PikachuController pikachuController;
    private NormalBotController normalBotController;
    private MasterYodaController masterYodaController;
    private SendMessageController sendMessageController;
    private ExitChatController exitChatController;
    private PastChatController pastChatController;

    private final JButton chatButton;
    private final JButton chatHistoryButton;
    private final JLabel username;
    private final JButton logOut;
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;
    private final JButton customBotButton;
    private final ChatViewModel chatViewModel;
    @SuppressWarnings({"checkstyle:DeclarationOrder", "checkstyle:VisibilityModifier"})
    private final PastChatViewModel pastChatViewModel;
    ChatView chatApp;
    PastChatView pastChatView;


    @SuppressWarnings({"checkstyle:RightCurly", "checkstyle:IllegalCatch", "checkstyle:CatchParameterName"})
    public LoggedInView(LoggedInViewModel loggedInViewModel, ChatViewModel chatViewModel,
                        PastChatViewModel pastChatViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.chatViewModel = chatViewModel;
        this.chatViewModel.addPropertyChangeListener(this);
        this.pastChatViewModel = pastChatViewModel;

        // Title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel title = new JLabel("Chatbot for");

        username = new JLabel();
        titlePanel.add(title);
        titlePanel.add(username);
        // Title label
        // final JLabel title = new JLabel("Character Chatbot for");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Password input field and change password button
        final JPanel passwordInfo = new JPanel();
        passwordInfo.add(passwordInputField);

        // Create radiobutton
        final JRadioButton normalBotButton = new JRadioButton("Normal Bot");
        final JRadioButton pikachuButton = new JRadioButton("Pikachu");
        final JRadioButton masterYodaButton = new JRadioButton("Master Yoda");
        final JRadioButton optimusPrimeButton = new JRadioButton("Optimus Prime");

        // Initialize buttons

        chatButton = new JButton("Chat");
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Starting Chat...");
            if (optimusPrimeButton.isSelected()) {
                optimusPrimeController.execute(username.getText());
            }
            else if (pikachuButton.isSelected()) {
                pikachuController.execute(username.getText());
            }
            else if (normalBotButton.isSelected()) {
                normalBotController.execute(username.getText());
            }
            else if (masterYodaButton.isSelected()) {
                masterYodaController.execute(username.getText());
            }
        });

        chatHistoryButton = new JButton("Past Chat");
        chatHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatHistoryButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Opening Past Chat...");

            pastChatView = new PastChatView(pastChatController, username.getText(), pastChatViewModel);
            pastChatView.setVisible(true);
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
        final JPanel defaultCharactorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JLabel defaultCharactors = new JLabel("Default Characters:");
        defaultCharactorsPanel.add(defaultCharactors);
        defaultCharactors.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        final JPanel defaultPanel = new JPanel();
        final JLabel selectCharactors = new JLabel("(Select a character and click chat)");

        final JPanel characterPanel = new JPanel(new GridLayout(2, 2));
        // ensure only one button can be selected
        final ButtonGroup group = new ButtonGroup();
        group.add(normalBotButton);
        group.add(pikachuButton);
        group.add(masterYodaButton);
        group.add(optimusPrimeButton);
        normalBotButton.setSelected(true);

        normalBotButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        pikachuButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        masterYodaButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        optimusPrimeButton.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        characterPanel.add(normalBotButton);
        characterPanel.add(pikachuButton);
        characterPanel.add(masterYodaButton);
        characterPanel.add(optimusPrimeButton);

        // defaultPanel.add(defaultCharactors);
        defaultPanel.add(characterPanel);
        defaultPanel.add(selectCharactors);
        // defaultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.Y_AXIS));
        // defaultCharactors.setAlignmentX(Component.RIGHT_ALIGNMENT);
        selectCharactors.setAlignmentX(Component.CENTER_ALIGNMENT);

        // chatPanel
        final JPanel chatControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chatControlPanel.add(chatButton);
        chatControlPanel.add(chatHistoryButton);

        // custompanel
        customBotButton = new JButton("Custom Bot");
        customBotButton.addActionListener(evt -> {
            toCustomViewController.switchToCustomBotView();
        });
        final JPanel customSettingsPanel = new JPanel();
        customSettingsPanel.add(customBotButton);
        // customSettingsPanel.add(changePassword);
        customBotButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // customSettingsPanel.setLayout(new BoxLayout(customSettingsPanel, BoxLayout.Y_AXIS));

        final JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.add(changePassword);
        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        // log out panel
        final JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logOut);

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // this.add(rightVerticalPanel, BorderLayout.EAST);
        this.add(titlePanel);
        this.add(defaultCharactorsPanel);
        this.add(defaultPanel);
        this.add(chatControlPanel);
        this.add(customSettingsPanel);
        this.add(changePasswordPanel);
        this.add(logoutPanel);

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
        else if (evt.getPropertyName().equals("new chat")) {

            final String setting = chatViewModel.getPrompt();
            sendMessageController.setSystemSetting(setting);

            final String username = chatViewModel.getUsername();

            chatApp = new ChatView(sendMessageController, username, chatViewModel, exitChatController);
            chatApp.setVisible(true);
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

    public void setToCustomViewController(ToCustomViewController toCustomViewController) {
        this.toCustomViewController = toCustomViewController;
    }

    public void setOptimusPrimeController(OptimusPrimeController optimusPrimeController) {
        this.optimusPrimeController = optimusPrimeController;
    }

    public void setPikachuController(PikachuController pikachuController) {
        this.pikachuController = pikachuController;
    }

    public void setNormalBotController(NormalBotController normalBotController) {
        this.normalBotController = normalBotController;
    }

    public void setMasterYodaController(MasterYodaController masterYodaController) {
        this.masterYodaController = masterYodaController;
    }

    public void setSendMessageController(SendMessageController sendMessageController) {
        this.sendMessageController = sendMessageController;
    }

    public void exitChatController(ExitChatController exitChatController) {
        this.exitChatController = exitChatController;
    }

    public void setPastChatController(PastChatController pastChatController) {
        this.pastChatController = pastChatController;
    }
}