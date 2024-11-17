package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.chat.ChatController;
import interface_adapter.chat.promptController;
import interface_adapter.customBot.CustomBotState;
import interface_adapter.customBot.CustomBotViewModel;
import interface_adapter.customBot.GoBackToLoggedInViewController;
import use_case.ChatService.ChatService;


public class CustomBotView extends JPanel implements PropertyChangeListener {
    private final String viewName = "customBot";
    private final CustomBotViewModel customBotViewModel;
    // private final LoggedInViewModel loggedInViewModel;
    // private final JLabel passwordErrorField = new JLabel();
    private final JButton chatButton;
    private final JButton backButton;
    private GoBackToLoggedInViewController goBackToLoggedInViewController;
    private final JTextField nameInputField = new JTextField(15);
    private final JTextField occupationInputField = new JTextField(15);

    public CustomBotView(CustomBotViewModel customBotViewModel) {
        this.customBotViewModel = customBotViewModel;
        this.customBotViewModel.addPropertyChangeListener(this);
        chatButton = new JButton("Chat");
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        chatButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Starting Chat...");
            final promptController prompcontroller = new promptController();
            final String setting = prompcontroller.creatPrompt(nameInputField.getText(), occupationInputField.getText());
            final ChatService chatService = new ChatService(setting);
            final ChatController chatController = new ChatController(chatService);
            final ChatBotSwingApp chatApp = new ChatBotSwingApp(chatController);
            chatApp.setVisible(true);
        });

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(evt -> {
            goBackToLoggedInViewController.switchToLoggedInView();
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

        nameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CustomBotState currentState = customBotViewModel.getState();
                currentState.setName(nameInputField.getText());
                customBotViewModel.setState(currentState);
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

        occupationInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CustomBotState currentState = customBotViewModel.getState();
                currentState.setOccupation(occupationInputField.getText());
                customBotViewModel.setState(currentState);
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

        this.add(title);
        this.add(inputPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CustomBotState state = (CustomBotState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(CustomBotState state) {
        nameInputField.setText(state.getName());
        occupationInputField.setText(state.getOccupation());
    }

    public String getViewName() {
        return viewName;
    }

    public void setToLoggedInView(GoBackToLoggedInViewController goBackToLoggedInViewController) {
        this.goBackToLoggedInViewController = goBackToLoggedInViewController;
    }
}
