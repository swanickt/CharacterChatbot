package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeView extends JPanel implements ActionListener {

    private final String viewName = "home view";

    private JButton logIn;
    private final JButton closeApp;
    private final JButton createAccount;

    public HomeView() {

        // Formatting the view and adding buttons/labels:

        final JLabel title1 = new JLabel("Welcome to Character Chatbot!");
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel title2 = new JLabel("(Beta Version)");
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel space = new JLabel(" ");
        space.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();

        createAccount = new JButton("Create Account");
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(createAccount);
        buttons.add(Box.createRigidArea(new Dimension(0, 11)));
        // The above line is to add space between buttons.

        logIn = new JButton("Go to Login");
        logIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logIn);
        buttons.add(Box.createRigidArea(new Dimension(0, 11)));
        // The above line is to add space between buttons.

        closeApp = new JButton("Close App");
        closeApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(closeApp);

        createAccount.addActionListener(this);
        logIn.addActionListener(this);
        closeApp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        this.add(title1);
        this.add(title2);
        this.add(space);
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }
}
