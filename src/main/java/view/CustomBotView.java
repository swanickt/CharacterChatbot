package view;

import java.awt.*;

import javax.swing.*;

import interface_adapter.chat.ChatController;
import interface_adapter.customBot.CustomBotViewModel;
import interface_adapter.customBot.GoBackToLogginViewController;
import use_case.ChatService.ChatService;


public class CustomBotView extends JPanel {
    private final String viewName = "customBot";
    private final CustomBotViewModel customBotViewModel;
    // private final LoggedInViewModel loggedInViewModel;
    // private final JLabel passwordErrorField = new JLabel();
    private final JButton chatButton;
    private final JButton backButton;
    private GoBackToLogginViewController goBackToLogginViewController;
    private final JTextField nameInputField = new JTextField(15);
    private final JTextField occupationInputField = new JTextField(15);

    public CustomBotView(CustomBotViewModel customBotViewModel) {
        this.customBotViewModel = customBotViewModel;
        // this.loggedInViewModel.addPropertyChangeListener(this);
        chatButton = new JButton("Chat");
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        chatButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Starting Chat...");
            final String setting = "";
            final ChatService chatService = new ChatService(setting);
            final ChatController chatController = new ChatController(chatService);
            final ChatBotSwingApp chatApp = new ChatBotSwingApp(chatController);
            chatApp.setVisible(true);
        });

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(evt -> {
            goBackToLogginViewController.switchToLoggedInView();
        });

        final JLabel title = new JLabel("Custom Bot Creation");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel inputPanel = new JPanel();
        final LabelTextPanel nameInput = new LabelTextPanel(
                new JLabel("name"), nameInputField);
        final LabelTextPanel occupationInput = new LabelTextPanel(
                new JLabel("occupation"), occupationInputField);
        inputPanel.add(nameInput);
        inputPanel.add(occupationInput);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        final JPanel buttons = new JPanel();
        buttons.add(chatButton);
        buttons.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(inputPanel);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setToLoggedInView(GoBackToLogginViewController goBackToLogginViewController) {
        this.goBackToLogginViewController = goBackToLogginViewController;
    }
}