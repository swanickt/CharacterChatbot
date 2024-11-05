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

import interface_adapter.home_view.HomeViewController;
import interface_adapter.home_view.HomeViewModel;

/**
 * The View for the program's home screen.
 */
public class HomeView extends JPanel implements ActionListener {

    private final String viewName = "home view";
    private final HomeViewModel homeViewModel;

    private final JButton logIn;
    private final JButton closeApp;
    private final JButton createAccount;

    private HomeViewController homeViewController;

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        // Formatting the view and adding buttons/labels:

        final JLabel title1 = new JLabel(HomeViewModel.TITLE1_LABEL);
        title1.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JLabel title2 = new JLabel(HomeViewModel.TITLE2_LABEL);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();

        createAccount = new JButton(HomeViewModel.CREATE_ACCOUNT_BUTTON_LABEL);
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(createAccount);
        buttons.add(Box.createRigidArea(new Dimension(0, 11)));
        // The above line is to add space between buttons.

        logIn = new JButton(HomeViewModel.LOGIN_BUTTON_LABEL);
        logIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(logIn);
        buttons.add(Box.createRigidArea(new Dimension(0, 11)));
        // The above line is to add space between buttons.

        closeApp = new JButton(HomeViewModel.CLOSE_APP_BUTTON_LABEL);
        closeApp.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(closeApp);

        createAccount.addActionListener(this);
        logIn.addActionListener(this);
        closeApp.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        this.add(title1);
        this.add(title2);
        this.add(Box.createRigidArea(new Dimension(0, 6)));
        // The above line is to add space between header and buttons.
        this.add(buttons);

    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logIn)) {
            homeViewController.switchToLoginView();
        }
        else if (evt.getSource().equals(createAccount)) {
            homeViewController.switchToSignupView();
        }
        else if (evt.getSource().equals(closeApp)) {
            System.exit(0);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setHomeViewController(HomeViewController controller) {
        this.homeViewController = controller;
    }

}