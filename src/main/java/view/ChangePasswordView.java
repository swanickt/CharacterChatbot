package view;

import interface_adapter.change_password.*;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChangePasswordView extends JPanel implements PropertyChangeListener {
    private final String viewName = "change password";

    private final ChangePasswordViewModel changePasswordViewModel;
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField reEnterPasswordInputField = new JPasswordField(15);
    private ChangePasswordController changePasswordController;
    private BackToLoggedInController backToLoggedInController;

    private final JButton changePassword;
    private final JButton cancel;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ChangePasswordViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(ChangePasswordViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel reEnterPasswordInfo = new LabelTextPanel(
                new JLabel(ChangePasswordViewModel.REPEAT_PASSWORD_LABEL), reEnterPasswordInputField);

        final JPanel buttons = new JPanel();
        changePassword = new JButton(ChangePasswordViewModel.CHANGE_PASSWORD_BUTTON_LABEL);
        buttons.add(changePassword);

        cancel = new JButton(ChangePasswordViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        backToLoggedInController.switchToLoggedInView();
                    }
                }
        );

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final ChangePasswordState currentState = changePasswordViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getPassword(),
                                currentState.getRepeatPassword(),
                                currentState.getUsername()
                        );
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                changePasswordViewModel.setState(currentState);
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

        reEnterPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ChangePasswordState currentState = changePasswordViewModel.getState();
                currentState.setRepeatPassword(new String(reEnterPasswordInputField.getPassword()));
                changePasswordViewModel.setState(currentState);
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
        this.add(passwordInfo);
        this.add(reEnterPasswordInfo);
        this.add(buttons);
    }

    //    @Override
    //    public void actionPerformed(ActionEvent evt) {
    //        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    //    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
        setFields(state);
        if (evt.getPropertyName().equals("password")) {
            // final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, "password updated for " + state.getUsername());
        }
        else if (evt.getPropertyName().equals("error")) {
            // final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getRepeatPasswordError());
        }
    }

    private void setFields(ChangePasswordState state) {
        passwordInputField.setText(state.getPassword());
        reEnterPasswordInputField.setText(state.getRepeatPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController controller) {
        this.changePasswordController = controller;
    }

    public void setBackToLoggedInController(BackToLoggedInController backToLoggedInController) {
        this.backToLoggedInController = backToLoggedInController;
    }
}
