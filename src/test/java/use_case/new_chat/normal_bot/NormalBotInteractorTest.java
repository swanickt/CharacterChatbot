package use_case.new_chat.normal_bot;

import entity.bot.BotFactory;
import entity.bot.NormalAI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link NormalBotInteractor}.
 * <p>
 * This test class covers the interactor's behavior, including successful execution,
 * handling of null input data, and validation of {@link NormalBotOutputData}.
 */
public class NormalBotInteractorTest {

    private NormalBotOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    /**
     * Sets up the mock dependencies required for the test cases.
     * <p>
     * The presenter captures the output of the interactor, and the factory provides
     * a mock Normal Bot instance. This setup method is executed before each test.
     */
    @BeforeEach
    void setup() {
        // Mock Presenter to capture outputs
        mockPresenter = new NormalBotOutputBoundary() {
            private boolean beginChatCalled = false;
            private NormalBotOutputData capturedOutputData;

            @Override
            public void beginChat(NormalBotOutputData normalBotOutputData) {
                beginChatCalled = true;
                capturedOutputData = normalBotOutputData;
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
             * Retrieves the {@link NormalBotOutputData} captured during the test.
             *
             * @return the captured {@link NormalBotOutputData}.
             */
            public NormalBotOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to generate a bot with a static prompt
        mockFactory = new BotFactory() {
            @Override
            public NormalAI create() {
                return new NormalAI() {
                    @Override
                    public String getPrompt() {
                        return "Hello! I'm your normal bot assistant.";
                    }
                };
            }
        };
    }

    /**
     * Tests the successful execution of the {@link NormalBotInteractor}.
     * <p>
     * Ensures the interactor correctly retrieves input data, generates a Normal Bot instance via the factory,
     * and calls the presenter's {@code beginChat} method with the expected output data.
     */
    @Test
    void testSuccessfulExecution() {
        // Arrange
        NormalBotInputData inputData = new NormalBotInputData("John");
        NormalBotInteractor interactor = new NormalBotInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((NormalBotOutputBoundary) mockPresenter).isBeginChatCalled(), "The beginChat method should have been called.");
        NormalBotOutputData outputData = ((NormalBotOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData, "Output data should not be null.");
        assertEquals("John", outputData.getUsername(), "Username should match the input data.");
        assertEquals("Hello! I'm your normal bot assistant.", outputData.getPrompt(), "Prompt should match the bot's prompt.");
    }

    /**
     * Tests the behavior of the {@link NormalBotInteractor} when null input data is provided.
     * <p>
     * Expects a {@link NullPointerException} to be thrown.
     */
    @Test
    void testNullInputData() {
        // Arrange
        NormalBotInteractor interactor = new NormalBotInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null), "Null input data should throw NullPointerException.");
    }

    /**
     * Tests the {@link NormalBotOutputData} class to ensure correct initialization and retrieval of fields.
     */
    @Test
    void testNormalBotOutputData() {
        // Arrange
        NormalBotOutputData outputData = new NormalBotOutputData("Alice", "Welcome to the chat with Normal Bot!");

        // Act & Assert
        assertEquals("Alice", outputData.getUsername(), "Username should match the constructor value.");
        assertEquals("Welcome to the chat with Normal Bot!", outputData.getPrompt(), "Prompt should match the constructor value.");
    }
}
