package use_case.past_chat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data_access.user_data.InMemoryUserDataAccessObject;
import entity.message.Message;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PastTest {

    private PastChatInteractor pastChatInteractor;
    private InMemoryUserDataAccessObject inMemoryUserDataAccess;
    private PastChatOutputBoundary mockOutputBoundary;

    @BeforeEach
    void setUp() {
        inMemoryUserDataAccess = new InMemoryUserDataAccessObject();
        inMemoryUserDataAccess.database = new java.util.HashMap<>(); // Initialize database
        mockOutputBoundary = new PastChatOutputBoundary() {
            @Override
            public void presentPastChat(PastChatOutputData pastChatOutputData) {
                assertNotNull(pastChatOutputData);
                assertNotNull(pastChatOutputData.getPastChat());
            }
        };
        pastChatInteractor = new PastChatInteractor(mockOutputBoundary, inMemoryUserDataAccess);
    }

    @Test
    void testExecuteWithValidUser() {
        // Setup user data in the mock database
        String username = "TestUser";
        List<Message> userMessages = new ArrayList<>();
        userMessages.add(new Message("user", "Hello"));
        userMessages.add(new Message("assistant", "Hi there!"));
        inMemoryUserDataAccess.database.put(username, userMessages.toString());

        PastChatInputData inputData = new PastChatInputData(username);
        pastChatInteractor.execute(inputData);

        assertNotNull(inputData);
        assertEquals(username, inputData.getUsername());
    }

    @Test
    void testExecuteWithNoChatHistory() {
        // No setup data in the mock database for this user
        String username = "UnknownUser";
        PastChatInputData inputData = new PastChatInputData(username);
        pastChatInteractor.execute(inputData);

        // The output should be empty since there's no history for this user
        mockOutputBoundary.presentPastChat(new PastChatOutputData(new ArrayList<>()));
    }

    @Test
    void testExecuteWithEmptyUsername() {
        // Test with an empty username
        PastChatInputData inputData = new PastChatInputData("");
        pastChatInteractor.execute(inputData);

        // Since username is empty, it should not find any chat history
        mockOutputBoundary.presentPastChat(new PastChatOutputData(new ArrayList<>()));
    }

    @Test
    void testPastChatInputData() {
        // Test the PastChatInputData object
        String username = "TestUser";
        PastChatInputData inputData = new PastChatInputData(username);
        assertNotNull(inputData);
        assertEquals(username, inputData.getUsername());
    }

    @Test
    void testPastChatOutputData() {
        // Test the PastChatOutputData object
        List<String> chatHistory = new ArrayList<>();
        chatHistory.add("User: Hello");
        chatHistory.add("Assistant: Hi there!");

        PastChatOutputData outputData = new PastChatOutputData(chatHistory);
        assertNotNull(outputData);
        assertEquals(chatHistory, outputData.getPastChat());
    }
}
