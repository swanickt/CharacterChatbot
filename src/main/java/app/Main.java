package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addChangePasswordView()
                                            .addHomeView()
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addCustomBotView()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addHomeViewUseCase()
                                            .addLoggedInUseCase()
                                            .addCustomBotUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}

// this is an example of the builder design pattern. Method main's sole
// responsibility is to build and start the engine. Helps with single
// responsibility principle.

