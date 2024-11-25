package use_case.send_message;

import data_access.gpt_api_calls.MockGPTapi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageTest {

    private MockGPTapi mockGPTapi;
    private SendMessageOutputBoundary mockOutputBoundary;
    private SendMessageInteractor sendMessageInteractor;

    @BeforeEach
    void setUp() {
        mockGPTapi = new MockGPTapi();
        mockGPTapi.history = new java.util.HashMap<>(); // Initialize the history map
        mockOutputBoundary = new SendMessageOutputBoundary() {
            @Override
            public void returnBotResponse(SendMessageOutputData sendMessageOutputData) {
                assertNotNull(sendMessageOutputData.getBotResponse());
                if (sendMessageOutputData.getBotResponseError()) {
                    assertEquals("Error: Unable to get response from GPT.", sendMessageOutputData.getBotResponse());
                } else {
                    assertFalse(sendMessageOutputData.getBotResponseError());
                }
            }
        };
        sendMessageInteractor = new SendMessageInteractor(mockOutputBoundary, mockGPTapi);
    }

    @Test
    void testExecuteValidInput() {
        SendMessageInputData inputData = new SendMessageInputData("Hello, how are you?");
        sendMessageInteractor.execute(inputData);
    }

    @Test
    void testExecuteInvalidInput() {
        SendMessageInputData inputData = new SendMessageInputData(""); // Empty input to simulate invalid input
        sendMessageInteractor.execute(inputData);
    }

    @Test
    void testSetSystemSetting() {
        String setting = "New system setting";
        sendMessageInteractor.setSystemSetting(setting);
        assertEquals(setting, mockGPTapi.setting);
    }

    @Test
    void testSendMessageInputDataObjectCreation() {
        // Test creating an object of SendMessageInputData
        String userInput = "Test message for input data";
        SendMessageInputData inputData = new SendMessageInputData(userInput);
        assertNotNull(inputData);
        assertEquals(userInput, inputData.getUserInput());
    }

    @Test
    void testSendMessageOutputDataObjectCreation() {
        // Test creating an object of SendMessageOutputData
        String botResponse = "Response from bot";
        boolean responseError = false;
        SendMessageOutputData outputData = new SendMessageOutputData(botResponse, responseError);
        assertNotNull(outputData);
        assertEquals(botResponse, outputData.getBotResponse());
        assertEquals(responseError, outputData.getBotResponseError());
    }

    @Test
    void testSendMessageInteractorObjectCreation() {
        // Test creating an object of SendMessageInteractor
        SendMessageInteractor interactor = new SendMessageInteractor(mockOutputBoundary, mockGPTapi);
        assertNotNull(interactor);
    }

    @Test
    void testSendMessageOutputDataWithError() {
        String botResponse = "Error: Unable to get response from GPT.";
        boolean responseError = true;
        SendMessageOutputData outputData = new SendMessageOutputData(botResponse, responseError);
        assertNotNull(outputData);
        assertEquals(botResponse, outputData.getBotResponse());
        assertEquals(responseError, outputData.getBotResponseError());
    }

    @Test
    void testExecuteWithMockInteraction() {
        // Test executing the interactor with mock API interaction
        SendMessageInputData inputData = new SendMessageInputData("Test user message");
        sendMessageInteractor.execute(inputData);
        assertTrue(mockGPTapi.history.containsKey("user"));
        assertEquals("Test user message", mockGPTapi.history.get("user"));
        assertTrue(mockGPTapi.history.containsKey("assistant"));
        assertEquals("response from GPT", mockGPTapi.history.get("assistant"));
    }

    @Test
    void testExecuteWithException() {
        // Simulate an exception in the mock API and verify error handling
        MockGPTapi faultyApi = new MockGPTapi() {
            @Override
            public String getChatbotResponse() {
                throw new RuntimeException("Simulated exception");
            }
        };
        faultyApi.history = new java.util.HashMap<>(); // Initialize the history map for faultyApi
        SendMessageInteractor interactorWithFaultyApi = new SendMessageInteractor(mockOutputBoundary, faultyApi);

        SendMessageInputData inputData = new SendMessageInputData("This will cause an error");
        interactorWithFaultyApi.execute(inputData);
    }
}
