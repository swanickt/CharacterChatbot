package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.MongoDBDataAccessObject;
import data_access.gpt_api_calls.GptApiCallBotResponseDataAccessObject;
import entity.bot.*;
import entity.user.CommonUserFactory;
import entity.user.UserFactory;
import interface_adapter.new_chat.ChatViewModel;
import interface_adapter.new_chat.custom_bot.CustomBotController;
import interface_adapter.new_chat.custom_bot.CustomBotPresenter;
import interface_adapter.new_chat.master_yoda.MasterYodaController;
import interface_adapter.new_chat.master_yoda.MasterYodaPresenter;
import interface_adapter.new_chat.normal_bot.NormalBotController;
import interface_adapter.new_chat.normal_bot.NormalBotPresenter;
import interface_adapter.new_chat.optimus_prime.OptimusPrimeController;
import interface_adapter.new_chat.optimus_prime.OptimusPrimePresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.BackToLoggedInController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.ChangePasswordViewModel;

import interface_adapter.new_chat.pikachu.PikachuController;
import interface_adapter.new_chat.pikachu.PikachuPresenter;
import interface_adapter.custom_bot_page.CustomBotPagePresenter;
import interface_adapter.custom_bot_page.CustomBotViewModel;
import interface_adapter.custom_bot_page.GoBackToLoggedInViewController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logged_in.ToCustomViewController;
import interface_adapter.logged_in.ToPasswordSettingsController;
import interface_adapter.home_view_buttons.GoToLoginController;
import interface_adapter.home_view_buttons.GoToSignUpController;
import interface_adapter.home_view_buttons.HomeViewModel;
import interface_adapter.home_view_buttons.HomeViewPresenter;
import interface_adapter.login.LoginCancelController;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.signup.SignupCancelController;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.new_chat.custom_bot.CustomBotInteractor;
import use_case.new_chat.custom_bot.CustomBotOutputBoundary;
import use_case.new_chat.master_yoda.MasterYodaInteractor;
import use_case.new_chat.master_yoda.MasterYodaOutputBoundary;
import use_case.new_chat.normal_bot.NormalBotInteractor;
import use_case.new_chat.normal_bot.NormalBotOutputBoundary;
import use_case.new_chat.optimus_prime.OptimusPrimeInputBoundary;
import use_case.new_chat.optimus_prime.OptimusPrimeInteractor;
import use_case.new_chat.optimus_prime.OptimusPrimeOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.new_chat.pikachu.PikachuInputBoundary;
import use_case.new_chat.pikachu.PikachuInteractor;
import use_case.new_chat.pikachu.PikachuOutputBoundary;
import use_case.exit_custom_bot_view.CustomViewInputBoundary;
import use_case.exit_custom_bot_view.CustomViewInteractor;
import use_case.exit_custom_bot_view.CustomViewOutputBoundary;
import use_case.home_view_buttons.HomeViewInputBoundary;
import use_case.home_view_buttons.HomeViewInteractor;
import use_case.home_view_buttons.HomeViewOutputBoundary;
import use_case.logged_in_buttons.LoggedInInputBoundary;
import use_case.logged_in_buttons.LoggedInInteractor;
import use_case.logged_in_buttons.LoggedInOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.ChangePasswordView;
import view.CustomBotView;
import view.HomeView;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final MongoDBDataAccessObject userDataAccessObject = new MongoDBDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private HomeView homeView;
    private HomeViewModel homeViewModel;
    private ChangePasswordView changePasswordView;
    private ChangePasswordViewModel changePasswordViewModel;
    private CustomBotViewModel customBotViewModel;
    private CustomBotView customBotView;
    private final ChatViewModel chatViewModel = new ChatViewModel();

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the change password view to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordView() {
        changePasswordViewModel = new ChangePasswordViewModel();
        changePasswordView = new ChangePasswordView(changePasswordViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;

    }

    /**
     * Adds the Home View to the application.
     * @return this builder
     */
    public AppBuilder addHomeView() {
        homeViewModel = new HomeViewModel();
        homeView = new HomeView(homeViewModel);
        cardPanel.add(homeView, homeView.getViewName());
        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel, chatViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the CustomBot View to the application.
     * @return this builder
     */
    public AppBuilder addCustomBotView() {
        customBotViewModel = new CustomBotViewModel();
        customBotView = new CustomBotView(customBotViewModel, chatViewModel);
        cardPanel.add(customBotView, customBotView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel, homeViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);

        final SignupCancelController signupCancelInteractor = new SignupCancelController(userSignupInteractor);
        signupView.setSignupCancelController(signupCancelInteractor);

        return this;
    }

    /**
     * Adds the LoggedIn Use Cases (buttons in logged in screen) to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInUseCase() {
        final LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(changePasswordViewModel,
                viewManagerModel, loggedInViewModel, customBotViewModel);
        final OptimusPrimeOutputBoundary optimusPrimeOutputBoundary = new OptimusPrimePresenter(chatViewModel);
        final LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(loggedInOutputBoundary);
        final OptimusPrimeInputBoundary optimusPrimeInteractor = new OptimusPrimeInteractor(optimusPrimeOutputBoundary,
                new OptimusPrimeFactory());
        final PikachuOutputBoundary pikachuOutputBoundary = new PikachuPresenter(chatViewModel);
        final PikachuInputBoundary pikachuInteractor = new PikachuInteractor(pikachuOutputBoundary,
                new PikachuFactory());
        final NormalBotOutputBoundary normalBotOutputBoundary = new NormalBotPresenter(chatViewModel);
        final NormalBotInteractor normalBotInteractor = new NormalBotInteractor(normalBotOutputBoundary,
                new NormalAIFactory());

        final MasterYodaOutputBoundary masterYodaOutputBoundary = new MasterYodaPresenter(chatViewModel);
        final MasterYodaInteractor masterYodaInteractor = new MasterYodaInteractor(masterYodaOutputBoundary,
                new MasterYodaFactory());

        final SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(chatViewModel);
        final SendMessageInteractor sendChatInteractor = new SendMessageInteractor(sendMessageOutputBoundary,
                new GptApiCallBotResponseDataAccessObject());

        final ToPasswordSettingsController controller1 = new ToPasswordSettingsController(loggedInInteractor);
        loggedInView.setToPasswordSettingsController(controller1);

        final ToCustomViewController controller2 = new ToCustomViewController(loggedInInteractor);
        loggedInView.setToCustomViewController(controller2);

        final OptimusPrimeController controller3 = new OptimusPrimeController(optimusPrimeInteractor);
        loggedInView.setOptimusPrimeController(controller3);

        final PikachuController controller4 = new PikachuController(pikachuInteractor);
        loggedInView.setPikachuController(controller4);

        final NormalBotController controller5 = new NormalBotController(normalBotInteractor);
        loggedInView.setNormalBotController(controller5);

        final MasterYodaController controller6 = new MasterYodaController(masterYodaInteractor);
        loggedInView.setMasterYodaController(controller6);

        final SendMessageController controller7 = new SendMessageController(sendChatInteractor);
        loggedInView.setSendMessageController(controller7);

        return this;
    }

    /**
     * Adds the HomeView Use Case (home screen buttons) to the application.
     * @return this builder
     */
    public AppBuilder addHomeViewUseCase() {
        final HomeViewOutputBoundary homeViewOutputBoundary = new HomeViewPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final HomeViewInputBoundary homeViewInteractor = new HomeViewInteractor(homeViewOutputBoundary);

        final GoToLoginController controller1 = new GoToLoginController(homeViewInteractor);
        homeView.setGoToLoginController(controller1);

        final GoToSignUpController controller2 = new GoToSignUpController(homeViewInteractor);
        homeView.setGoToSignUpController(controller2);

        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, homeViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);

        final LoginCancelController cancelController = new LoginCancelController(loginInteractor);
        loginView.setCancelController(cancelController);
        return this;

    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel, changePasswordViewModel, viewManagerModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
        final BackToLoggedInController backToLoggedInController =
                new BackToLoggedInController(changePasswordInteractor);
        changePasswordView.setBackToLoggedInController(backToLoggedInController);
        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        changePasswordView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, homeViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Custom Bot Use Case to the application.
     * @return this builder
     */
    public AppBuilder addCustomBotUseCase() {
        final CustomViewOutputBoundary customViewOutputBoundary = new CustomBotPagePresenter(loggedInViewModel,
                viewManagerModel, customBotViewModel);

        final CustomViewInputBoundary customViewInteractor = new CustomViewInteractor(customViewOutputBoundary);

        final GoBackToLoggedInViewController controller = new GoBackToLoggedInViewController(customViewInteractor);
        customBotView.setToLoggedInView(controller);

        final CustomBotOutputBoundary customBotOutputBoundary = new CustomBotPresenter(chatViewModel);
        final CustomBotInteractor customBotInteractor = new CustomBotInteractor(customBotOutputBoundary,
                new CustomBotFactory());

        final CustomBotController controller1 = new CustomBotController(customBotInteractor);
        customBotView.setCustomBotController(controller1);

        final SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(chatViewModel);
        final SendMessageInteractor sendChatInteractor = new SendMessageInteractor(sendMessageOutputBoundary,
                new GptApiCallBotResponseDataAccessObject());

        final SendMessageController controller2 = new SendMessageController(sendChatInteractor);
        customBotView.setSendMessageController(controller2);

        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the HomeView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Character Chatbot");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
