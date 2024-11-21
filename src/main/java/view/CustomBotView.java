/**
 * CustomBotView is a JPanel that represents the user interface for creating a custom chatbot.
 * It includes input fields for bot name and occupation, and buttons to start a chat or go back.
 * This class listens for property changes from a CustomBotViewModel to dynamically update its fields.
 */
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
    private final JButton chatButton;
    private final JButton backButton;
    private GoBackToLoggedInViewController goBackToLoggedInViewController;
    private final JTextField nameInputField = new JTextField(15);
    private final JTextField occupationInputField = new JTextField(15);

    /**
     * Constructs the CustomBotView.
     *
     * @param customBotViewModel the ViewModel that holds the state for the custom bot creation view.
     */
    public CustomBotView(CustomBotViewModel customBotViewModel) {
        this.customBotViewModel = customBotViewModel;
        this.customBotViewModel.addPropertyChangeListener(this);

        // Initialize buttons and their action listeners
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

        // Build the UI components
        final JLabel title = new JLabel("Custom Bot Creation");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input panel for bot details
        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        final JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        final JLabel nameLabel = new JLabel("Name:");
        final JLabel nameInfoIcon = createInfoIcon("Enter the name "
                + "of your custom bot or character.");
        namePanel.add(nameLabel);
        namePanel.add(nameInputField);
        namePanel.add(nameInfoIcon);

        final JPanel occupationPanel = new JPanel();
        occupationPanel.setLayout(new FlowLayout());
        final JLabel occupationLabel = new JLabel("Occupation:");
        final JLabel occupationInfoIcon = createInfoIcon("Enter the "
                + "occupation of your custom bot.");
        occupationPanel.add(occupationLabel);
        occupationPanel.add(occupationInputField);
        occupationPanel.add(occupationInfoIcon);

        inputPanel.add(namePanel);
        inputPanel.add(occupationPanel);

        final JPanel buttons = new JPanel();
        buttons.add(chatButton);
        buttons.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add document listeners to update ViewModel state dynamically
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

    /**
     * Handles property changes in the CustomBotViewModel to update the view dynamically.
     *
     * @param evt the PropertyChangeEvent that contains the updated state.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CustomBotState state = (CustomBotState) evt.getNewValue();
        setFields(state);
    }

    /**
     * Updates the input fields based on the provided CustomBotState.
     *
     * @param state the current state of the CustomBotViewModel.
     */
    private void setFields(CustomBotState state) {
        nameInputField.setText(state.getName());
        occupationInputField.setText(state.getOccupation());
    }

    /**
     * Returns the name of this view.
     *
     * @return the name of the view.
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the controller to switch back to the logged-in view.
     *
     * @param goBackToLoggedInViewController1 the controller for navigating to the logged-in view.
     */
    public void setToLoggedInView(GoBackToLoggedInViewController goBackToLoggedInViewController1) {
        this.goBackToLoggedInViewController = goBackToLoggedInViewController1;
    }

    /**
     * Creates an informational icon with a tooltip.
     *
     * @param tooltipText the text to display as a tooltip for the icon.
     * @return a JLabel containing the icon and tooltip.
     */
    private JLabel createInfoIcon(String tooltipText) {
        final JLabel label = new JLabel("(ℹ)", SwingConstants.CENTER);
        label.setToolTipText(tooltipText);
        return label;
        }
}

