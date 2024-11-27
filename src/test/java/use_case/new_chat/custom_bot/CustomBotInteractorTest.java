package use_case.new_chat.custom_bot;

import entity.bot.CustomBot;
import entity.bot.CustomBotFactory;
import entity.bot.Bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CustomBotInteractor}.
 * Tests cover successful execution, null input handling, empty bot name and occupation handling,
 * and validation of {@link CustomBotOutputData}.
 */
public class CustomBotInteractorTest {

    private CustomBotOutputBoundary mockPresenter;
    private CustomBotFactory mockFactory;

    /**
     * Sets up the mock dependencies required for the test cases.
     * <p>
     * The presenter captures the output of the interactor and the factory provides a mock bot instance.
     * This setup method is executed before each test.
     */
    @BeforeEach
    void setup() {
        // Mock Presenter to validate output logic
        mockPresenter = new CustomBotOutputBoundary() {
            private boolean beginChatCalled = false;
            private CustomBotOutputData capturedOutputData;

            @Override
            public void beginChat(CustomBotOutputData customBotOutputData) {
                beginChatCalled = true;
                capturedOutputData = customBotOutputData;
            }

            /**
             * Checks whether the {@code beginChat} method was called during the test.
             *
             * @return true if {@code beginChat} was called, false otherwise.
             */
            public boolean isBeginChatCalled() {
                return beginChatCalled;
            }

            /**
             * Retrieves the {@link CustomBotOutputData} captured during the test.
             *
             * @return the captured {@link CustomBotOutputData}.
             */
            public CustomBotOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to validate bot creation
        mockFactory = new CustomBotFactory() {
            @Override
            public CustomBot create(String name, String occupation) {
                return new CustomBot(name, occupation) {
                    @Override
                    public String getPrompt() {
                        return "Welcome to chat with " + name + ", the " + occupation + " bot!";
                    }
                };
            }
        };
    }

    /**
     * Tests the successful execution of the {@link CustomBotInteractor}.
     * <p>
     * Ensures the interactor correctly retrieves input data, generates a bot via the factory,
     * and calls the presenter's {@code beginChat} method with the expected output data.
     */
    @Test
    void testSuccessfulExecution() {
        // Arrange
        CustomBotInputData inputData = new CustomBotInputData("Alice", "HelperBot", "Assistant");
        CustomBotInteractor interactor = new CustomBotInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((CustomBotOutputBoundary) mockPresenter).isBeginChatCalled());
        CustomBotOutputData outputData = ((CustomBotOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData);
        assertEquals("Alice", outputData.getUsername());
        assertEquals("Welcome to chat with HelperBot, the Assistant bot!", outputData.getPrompt());
    }

    /**
     * Tests the behavior of the {@link CustomBotInteractor} when null input data is provided.
     * <p>
     * Expects a {@link NullPointerException} to be thrown.
     */
    @Test
    void testNullInputData() {
        // Arrange
        CustomBotInteractor interactor = new CustomBotInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null));
    }

    /**
     * Tests the handling of empty bot name and occupation in the {@link CustomBotInteractor}.
     * <p>
     * Ensures the interactor generates a valid output even when the bot's name and occupation are empty.
     */
    @Test
    void testEmptyBotNameAndOccupation() {
        // Arrange
        CustomBotInputData inputData = new CustomBotInputData("Alice", "", "");
        CustomBotInteractor interactor = new CustomBotInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((CustomBotOutputBoundary) mockPresenter).isBeginChatCalled());
        CustomBotOutputData outputData = ((CustomBotOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData);
        assertEquals("Alice", outputData.getUsername());
        assertEquals("Welcome to chat with , the  bot!", outputData.getPrompt());
    }

    /**
     * Tests the {@link CustomBotOutputData} class to ensure correct initialization and retrieval of fields.
     */
    @Test
    void testCustomBotOutputData() {
        // Arrange
        CustomBotOutputData outputData = new CustomBotOutputData("Alice", "Welcome to the chat!");

        // Act & Assert
        assertEquals("Alice", outputData.getUsername());
        assertEquals("Welcome to the chat!", outputData.getPrompt());
    }
}
