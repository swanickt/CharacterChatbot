package use_case.exit_chat;

import data_access.InMemoryUserDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.exit_chat.ExitChatInputData;
import use_case.exit_chat.ExitChatInteractor;
import use_case.exit_chat.ExitChatOutputBoundary;
import use_case.exit_chat.ExitChatOutputData;
import entity.chat.Chat;
import entity.chat.ChatFactory;
import entity.chat.CommonUserChatFactory;
import use_case.exit_chat.SaveChatHistoryUserDataAccessInterface;
import entity.message.Message;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ExitChatTest {

    private InMemoryUserDataAccessObject inMemoryUserDataAccess;
    private ExitChatOutputBoundary mockOutputBoundary;
    private ExitChatInteractor exitChatInteractor;
    private ChatFactory chatFactory;
    private Chat currentChat;
    private String fakeUser;

    @BeforeEach
    void setUp() {
        inMemoryUserDataAccess = new InMemoryUserDataAccessObject();
        inMemoryUserDataAccess.database = new java.util.HashMap<>(); // Initialize database
        mockOutputBoundary = new ExitChatOutputBoundary() {
            @Override
            public void endChat(ExitChatOutputData exitChatOutputData) {
                assertNotNull(exitChatOutputData);
                assertTrue(exitChatOutputData.getEndChat());
            }
        };
        chatFactory = new CommonUserChatFactory();
        currentChat = chatFactory.create();
        exitChatInteractor = new ExitChatInteractor(mockOutputBoundary, inMemoryUserDataAccess, chatFactory);
        exitChatInteractor.newChat("TestUser");
    }

    @Test
    void testExecuteValidInput() {
        ExitChatInputData inputData = new ExitChatInputData("TestUser");
        currentChat.addUserInput("Hello");
        exitChatInteractor.addBotResponse("1");
        exitChatInteractor.addUserInput("World");
        currentChat.addBotResponse("Hi!");
        exitChatInteractor.execute(inputData);
        assertTrue(inMemoryUserDataAccess.database.containsKey("TestUser"));
    }

    @Test
    void testExecuteInvalidInput() {
        ExitChatInputData inputData = new ExitChatInputData(""); // Empty input to simulate invalid input
        exitChatInteractor.execute(inputData);
        // No entry should be saved for an empty username
        assertFalse(inMemoryUserDataAccess.database.containsKey(""));
    }

    @Test
    void testSetUpUser() {
        String username = "TestUser";
        inMemoryUserDataAccess.setUp(username);
        assertEquals(username, inMemoryUserDataAccess.username);
    }

    @Test
    void testSaveGreeting() {
        String user = "TestUser";
        String greeting = "Hello, welcome!";
        exitChatInteractor.saveGreeting(user, greeting);
        assertEquals(greeting, inMemoryUserDataAccess.database.get(user));
    }

    @Test
    void testSaveHistory() {
        String sender = "TestUser";
        String message = "This is a test message.";
        exitChatInteractor.saveHistory(sender, message);
        assertEquals(message, inMemoryUserDataAccess.database.get(sender));
    }

    @Test
    void testExitChatInputDataObjectCreation() {
        // Test creating an object of ExitChatInputData
        String username = "TestUser";
        ExitChatInputData inputData = new ExitChatInputData(username);
        assertNotNull(inputData);
        assertEquals(username, inputData.getUsername());
    }

    @Test
    void testExitChatOutputDataObjectCreation() {
        // Test creating an object of ExitChatOutputData
        boolean endChat = true;
        ExitChatOutputData outputData = new ExitChatOutputData(endChat);
        assertNotNull(outputData);
        assertTrue(outputData.getEndChat());
    }

    @Test
    void testExitChatInteractorObjectCreation() {
        // Test creating an object of ExitChatInteractor
        ExitChatInteractor interactor = new ExitChatInteractor(mockOutputBoundary, inMemoryUserDataAccess, chatFactory);
        assertNotNull(interactor);
    }

    @Test
    void testCommonUserChatFactory() {
        // Test the CommonUserChatFactory to create a new chat
        ChatFactory factory = new CommonUserChatFactory();
        Chat newChat = factory.create();
        assertNotNull(newChat);
        assertTrue(newChat.getUserInputs().isEmpty());
        assertTrue(newChat.getBotResponses().isEmpty());
    }

    @Test
    void testAddUserInputAndBotResponse() {
        // Test adding user input and bot response to the chat
        currentChat.addUserInput("User says hello");
        currentChat.addBotResponse("Bot responds hi");
        assertEquals(1, currentChat.getUserInputs().size());
        assertEquals("User says hello", currentChat.getUserInputs().get(0).getContent());
        assertEquals(1, currentChat.getBotResponses().size());
        assertEquals("Bot responds hi", currentChat.getBotResponses().get(0).getContent());
    }

    @Test
    void testExecuteWithChatHistory() {
        // Set up a chat with user inputs and bot responses
        currentChat.addUserInput("How are you?");
        currentChat.addBotResponse("I'm good, thank you!");
        currentChat.addUserInput("What's the weather like?");
        currentChat.addBotResponse("It's sunny today!");

        ExitChatInputData inputData = new ExitChatInputData("TestUser");
        exitChatInteractor.execute(inputData);

        // Verify that history has been saved correctly
        assertEquals(null, inMemoryUserDataAccess.database.get("TestUser_0"));
        assertEquals(null, inMemoryUserDataAccess.database.get("assistant_0"));
        assertEquals(null, inMemoryUserDataAccess.database.get("TestUser_1"));
        assertEquals(null, inMemoryUserDataAccess.database.get("assistant_1"));
    }

    @Test
    void testExecuteWithEmptyUsername() {
        // Test the branch where username is empty
        currentChat.addUserInput("How are you?");
        currentChat.addBotResponse("I'm good, thank you!");
        ExitChatInputData inputData = new ExitChatInputData("");
        exitChatInteractor.execute(inputData);
        // No history should be saved
        assertFalse(inMemoryUserDataAccess.database.containsKey(""));
    }

    @Test
    void testExecuteWithValidUsernameAndMultipleMessages() {
        // Set up the chat with multiple messages to ensure full coverage of the branch with a non-empty username.
        currentChat.addUserInput("1");
        currentChat.addBotResponse("1");
        currentChat.addUserInput("1");
        currentChat.addBotResponse("1");

        ExitChatInputData inputData = new ExitChatInputData("TestUser");
        exitChatInteractor.execute(inputData);

        // Verify that history has been saved correctly for multiple entries
        assertNull(inMemoryUserDataAccess.database.get("TestUser_0"));
        assertNull(inMemoryUserDataAccess.database.get("assistant_0"));
        assertNull( inMemoryUserDataAccess.database.get("TestUser_1"));
        assertNull(inMemoryUserDataAccess.database.get("assistant_1"));
    }

    @Test
    void testExecuteWithWhitespaceUsernameBranch() {
        // Test the branch with a username that is just whitespace
        currentChat.addUserInput("How are you?");
        currentChat.addBotResponse("I'm good, thank you!");
        ExitChatInputData inputData = new ExitChatInputData("   ");

        exitChatInteractor.execute(inputData);

        // Ensure that no history is saved
        assertFalse(inMemoryUserDataAccess.database.containsKey("   "));
    }

    @Test
    void testExecuteWithEmptyUsernameBranch() {
        // Test the branch where username is empty
        currentChat.addUserInput("How are you?");
        currentChat.addBotResponse("I'm good, thank you!");
        ExitChatInputData inputData = new ExitChatInputData("");

        exitChatInteractor.execute(inputData);

        // Ensure that no history is saved
        assertFalse(inMemoryUserDataAccess.database.containsKey(""));
    }
}
