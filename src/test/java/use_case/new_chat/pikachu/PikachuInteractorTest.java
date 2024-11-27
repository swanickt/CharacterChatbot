package use_case.new_chat.pikachu;

import entity.bot.Bot;
import entity.bot.BotFactory;
import entity.bot.Pikachu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PikachuInteractor}.
 * <p>
 * This test class covers the interactor's behavior, including successful execution,
 * handling of null input data, and validation of {@link PikachuOutputData}.
 */
public class PikachuInteractorTest {

    private PikachuOutputBoundary mockPresenter;
    private BotFactory mockFactory;

    /**
     * Sets up the mock dependencies required for the test cases.
     * <p>
     * The presenter captures the output of the interactor, and the factory provides
     * a mock Pikachu bot instance. This setup method is executed before each test.
     */
    @BeforeEach
    void setup() {
        // Mock Presenter to capture outputs
        mockPresenter = new PikachuOutputBoundary() {
            private boolean beginChatCalled = false;
            private PikachuOutputData capturedOutputData;

            @Override
            public void beginChat(PikachuOutputData pikachuOutputData) {
                beginChatCalled = true;
                capturedOutputData = pikachuOutputData;
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
             * Retrieves the {@link PikachuOutputData} captured during the test.
             *
             * @return the captured {@link PikachuOutputData}.
             */
            public PikachuOutputData getCapturedOutputData() {
                return capturedOutputData;
            }
        };

        // Mock Factory to generate a bot with a static prompt
        mockFactory = new BotFactory() {
            @Override
            public Pikachu create() {
                return new Pikachu() {
                    @Override
                    public String getPrompt() {
                        return "Pika Pika! Let's chat!";
                    }
                };
            }
        };
    }

    /**
     * Tests the successful execution of the {@link PikachuInteractor}.
     * <p>
     * Ensures the interactor correctly retrieves input data, generates a Pikachu bot via the factory,
     * and calls the presenter's {@code beginChat} method with the expected output data.
     */
    @Test
    void testSuccessfulExecution() {
        // Arrange
        PikachuInputData inputData = new PikachuInputData("Ash");
        PikachuInteractor interactor = new PikachuInteractor(mockPresenter, mockFactory);

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(((PikachuOutputBoundary) mockPresenter).isBeginChatCalled(), "The beginChat method should have been called.");
        PikachuOutputData outputData = ((PikachuOutputBoundary) mockPresenter).getCapturedOutputData();
        assertNotNull(outputData, "Output data should not be null.");
        assertEquals("Ash", outputData.getUsername(), "Username should match the input data.");
        assertEquals("Pika Pika! Let's chat!", outputData.getPrompt(), "Prompt should match the bot's prompt.");
    }

    /**
     * Tests the behavior of the {@link PikachuInteractor} when null input data is provided.
     * <p>
     * Expects a {@link NullPointerException} to be thrown.
     */
    @Test
    void testNullInputData() {
        // Arrange
        PikachuInteractor interactor = new PikachuInteractor(mockPresenter, mockFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> interactor.execute(null), "Null input data should throw NullPointerException.");
    }

    /**
     * Tests the {@link PikachuOutputData} class to ensure correct initialization and retrieval of fields.
     */
    @Test
    void testPikachuOutputData() {
        // Arrange
        PikachuOutputData outputData = new PikachuOutputData("Misty", "Pika! Pika!");

        // Act & Assert
        assertEquals("Misty", outputData.getUsername(), "Username should match the constructor value.");
        assertEquals("Pika! Pika!", outputData.getPrompt(), "Prompt should match the constructor value.");
    }
}
