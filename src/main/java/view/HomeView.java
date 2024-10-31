package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "home view";

    private final JButton logIn;
    private final JButton closeApp;
    private final JButton signUp;

    public HomeView() {

        final JLabel title = new JLabel(HomeView.name());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();

        signUp = new JButton("Sign Up");
        buttons.add(signUp);
        logIn = new JButton("Go to Login");
        buttons.add(logIn);
        closeApp = new JButton("Close App");
        buttons.add(closeApp);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(title);
        this.add(buttons);

    }

    /**
     * Formats the header for the home screen.
     * @return the string version of the home screen header.
     */
    private static String name() {
        final String line1 = "Welcome to Character Chatbot!";
        final String line2 = "(Beta Version)";
        final int padding = line1.length()/(4);
        return String.format("%s%n%" + padding + "s%s", line1, "", line2);
    }
}
