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
            final String setting = prompcontroller.creatPrompt(nameInputField.getText(),
                    occupationInputField.getText());
            final ChatService chatService = new ChatService(setting);
            final ChatController chatController = new ChatController(chatService);
            final ChatBotSwingApp chatApp = new ChatBotSwingApp(chatController, "");
            chatApp.setVisible(true);
        });

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(evt -> {
            goBackToLoggedInViewController.switchToLoggedInView();
        });

        final JLabel title = new JLabel("Custom Bot Creation");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Panel
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // final JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        final JLabel nameLabel = new JLabel("Name:");
        final JLabel nameInfoIcon = createInfoIcon("Enter the name of your custom bot or character.");
        namePanel.add(nameLabel);
        namePanel.add(nameInputField);
        namePanel.add(nameInfoIcon);

        // Occupation Field with Icon
        // final JPanel occupationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel occupationPanel = new JPanel();
        occupationPanel.setLayout(new FlowLayout());
        final JLabel occupationLabel = new JLabel("Occupation:");
        final JLabel occupationInfoIcon = createInfoIcon("Enter the occupation of your custom bot.");
        occupationPanel.add(occupationLabel);
        occupationPanel.add(occupationInputField);
        occupationPanel.add(occupationInfoIcon);

        // Add panels to input panel
        inputPanel.add(namePanel);
        inputPanel.add(occupationPanel);

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

    public void setToLoggedInView(GoBackToLoggedInViewController goBackToLoggedInViewController1) {
        this.goBackToLoggedInViewController = goBackToLoggedInViewController1;
    }

    private JLabel createInfoIcon(String tooltipText) {
        try {
            ImageIcon icon = new ImageIcon("/view/info_icon.png");
            final Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            final JLabel label = new JLabel("ℹ", new ImageIcon(scaledImage), SwingConstants.CENTER);
            // JLabel label = new JLabel(icon, SwingConstants.CENTER);
            label.setToolTipText(tooltipText);
            return label;
        } catch (Exception e) {
            System.out.println("Icon not found. Using fallback text.");
            final JLabel fallbackLabel = new JLabel("(i)");
            fallbackLabel.setToolTipText(tooltipText);
            return fallbackLabel;
        }
    }
}
